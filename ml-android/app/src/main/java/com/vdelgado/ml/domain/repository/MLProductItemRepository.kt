package com.vdelgado.ml.domain.repository

import com.vdelgado.ml.domain.model.Result
import com.vdelgado.ml.domain.model.MLProductItem

interface MLProductItemRepository {
    suspend fun getProductDetail(productItem: String): Result<MLProductItem>
}