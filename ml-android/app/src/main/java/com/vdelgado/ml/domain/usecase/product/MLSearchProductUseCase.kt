package com.vdelgado.ml.domain.usecase.product

import androidx.paging.PagingData
import com.vdelgado.ml.domain.model.MLProduct
import com.vdelgado.ml.domain.repository.MLSearchProductRepository
import kotlinx.coroutines.flow.Flow

class MLSearchProductUseCase(private val repository: MLSearchProductRepository) {
    operator fun invoke(searchQuery: String): Flow<PagingData<MLProduct>> {
        return repository.searchProduct(searchQuery)
    }
}