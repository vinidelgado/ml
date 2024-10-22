package com.vdelgado.ml.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vdelgado.ml.data.remote.common.MLServiceApi
import com.vdelgado.ml.data.remote.data.MLInstallmentsResponse
import com.vdelgado.ml.data.remote.data.MLShippingResponse
import com.vdelgado.ml.domain.model.MLProductFormatted
import okio.IOException
import retrofit2.HttpException
import timber.log.Timber
import java.text.NumberFormat
import java.util.Locale

class SearchMLProductsPagingSourceImpl(
    private val api: MLServiceApi,
    private val searchQuery: String,
) : PagingSource<Int, MLProductFormatted>() {

    private var totalProductCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MLProductFormatted> {
        val page = params.key ?: 0
        return try {
            val apiResponse = api.searchProduct(
                query = searchQuery,
                offset = page,
                limit = 10
            )

            totalProductCount += apiResponse.results.size

            val products = apiResponse.results.distinctBy { it.id }

            val productsFormatted = products.map { product ->
                MLProductFormatted(
                    title = product.title,
                    originalPrice = discountPriceIsAvailable(
                        product.currencyId,
                        product.originalPrice
                    ),
                    price = formatPriceWithCurrencyIndicator(product.currencyId, product.price),
                    freeShipping = isFreeShipping(product.shipping),
                    imageUrl = product.thumbnail ?: "",
                    installments = isInstallmentsAvailable(product.installments),
                    itemId = product.id,
                    sellerName = product.officialStoreName ?: ""
                )
            }

            LoadResult.Page(
                data = productsFormatted,
                nextKey = if (totalProductCount == apiResponse.paging.total) {
                    null
                } else {
                    page + 1
                },
                prevKey = null
            )
        } catch (e: IOException) {
            Timber.e(e)
            LoadResult.Error(e)
        } catch (e: HttpException) {
            Timber.e(e)
            LoadResult.Error(e)
        } catch (e: Exception) {
            Timber.e(e)
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MLProductFormatted>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
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

    private fun isInstallmentsAvailable(installments: MLInstallmentsResponse?): String {
        if (installments != null) {
            if (installments.quantity > 0 && installments.amount > 0) {
                val amountFormatted =
                    formatPriceWithCurrencyIndicator(installments.currencyId, installments.amount)
                val hasNotRate = if (installments.rate > 0) "" else "sem juros"
                return "em ${installments.quantity}x $amountFormatted $hasNotRate"
            }
        }
        return ""
    }
}