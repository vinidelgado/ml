package com.vdelgado.ml.data.remote

import com.vdelgado.ml.data.remote.data.MLProductItemResponse
import com.vdelgado.ml.domain.repository.MLProductItemRepository
import retrofit2.HttpException
import java.io.IOException

class MLProductItemRepositoryImpl(
    private val api: MLServiceApi,
) : MLProductItemRepository {
    override suspend fun getProductDetail(productItem: String): MLProductItemResponse {
        return try {
            api.searchProductDetail(productItem)
        } catch (e: IOException) {
            throw (e)
        } catch (e: HttpException) {
            throw (e)
        }
    }
}