package com.vdelgado.ml.domain.repository

import com.vdelgado.ml.data.remote.data.MLProductItemResponse

interface MLProductItemRepository {
    suspend fun getProductDetail(productItem: String): MLProductItemResponse
}