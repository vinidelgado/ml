package com.vdelgado.ml.domain.usecase.product

import com.vdelgado.ml.domain.model.Result
import com.vdelgado.ml.domain.model.MLProductItem
import com.vdelgado.ml.domain.repository.MLProductItemRepository

class MLProductItemUseCase(private val repository: MLProductItemRepository) {
    suspend operator fun invoke(productItem: String): Result<MLProductItem> {
        return repository.getProductDetail(productItem)
    }
}