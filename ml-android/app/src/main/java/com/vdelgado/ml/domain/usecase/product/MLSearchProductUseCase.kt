package com.vdelgado.ml.domain.usecase.product

import androidx.paging.PagingData
import com.vdelgado.ml.domain.model.MLProductScreenFormatted
import com.vdelgado.ml.domain.repository.MLSearchProductRepository
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

class MLSearchProductUseCase(private val repository: MLSearchProductRepository) {
    operator fun invoke(searchQuery: String): Flow<PagingData<MLProductScreenFormatted>> {
        Timber.v("MLSearchProductUseCase")
        return repository.searchProduct(searchQuery)
    }
}