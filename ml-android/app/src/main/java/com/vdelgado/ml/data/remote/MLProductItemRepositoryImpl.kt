package com.vdelgado.ml.data.remote

import com.vdelgado.ml.data.remote.common.MLServiceApi
import com.vdelgado.ml.data.remote.data.MLShippingResponse
import com.vdelgado.ml.domain.model.MLProductItem
import com.vdelgado.ml.domain.model.Result
import com.vdelgado.ml.domain.repository.MLProductItemRepository
import retrofit2.HttpException
import java.io.IOException
import java.text.NumberFormat
import java.util.Locale

class MLProductItemRepositoryImpl(
    private val api: MLServiceApi,
) : MLProductItemRepository {
    override suspend fun getProductDetail(productItem: String): Result<MLProductItem> {
        return try {
            val response = api.searchProductDetail(productItem)
            val mlProductItem = MLProductItem(
                condition = response.condition,
                title = response.title,
                itemId = response.id,
                originalPrice = discountPriceIsAvailable(
                    response.currencyId,
                    response.originalPrice
                ),
                price = formatPriceWithCurrencyIndicator(response.currencyId, response.price),
                freeShipping = response.shipping?.freeShipping ?: false,
                quantity = response.initialQuantity,
                warranty = response.warranty ?: "",
                pictures = response.pictures.map {
                    it.url
                }
            )

            Result.Success.Content(mlProductItem)

        } catch (e: IOException) {
            Result.Failure(e)
        } catch (httpException: HttpException) {
            Result.Success.Error(
                code = httpException.code(),
                message = httpException.message(),
                body = httpException.response()?.errorBody()?.string()
            )
        }
    }

    private fun isFreeShipping(mlShipping: MLShippingResponse?) = mlShipping?.freeShipping ?: false

    private fun discountPriceIsAvailable(currencyId: String?, originalPrice: Double?): String {
        val price = originalPrice ?: 0.0
        return formatPriceWithCurrencyIndicator(currencyId, price)
    }

    private fun formatPriceWithCurrencyIndicator(currency: String?, price: Double?): String {
        val value = price ?: 0.0
        val moneyFormat = when (currency) {
            "BRL" -> NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
            else -> NumberFormat.getCurrencyInstance(Locale.US)
        }
        return if (value > 0) {
            moneyFormat.format(value).toString()
        } else {
            ""
        }
    }

}