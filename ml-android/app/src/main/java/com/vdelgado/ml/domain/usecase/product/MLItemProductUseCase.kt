package com.vdelgado.ml.domain.usecase.product

import com.vdelgado.ml.data.remote.data.MLProductItemResponse
import com.vdelgado.ml.domain.repository.MLProductItemRepository
import kotlinx.coroutines.flow.Flow

class MLItemProductUseCase(private val repository: MLProductItemRepository) {
    suspend operator fun invoke(productItem: String): MLProductItemResponse {
        return repository.getProductDetail(productItem)
    }
}