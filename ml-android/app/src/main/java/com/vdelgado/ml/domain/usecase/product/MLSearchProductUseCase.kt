package com.vdelgado.ml.domain.usecase.product

import androidx.paging.PagingData
import com.vdelgado.ml.domain.model.MLProductFormatted
import com.vdelgado.ml.domain.repository.MLSearchProductRepository
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

class MLSearchProductUseCase(private val repository: MLSearchProductRepository) {
    operator fun invoke(searchQuery: String): Flow<PagingData<MLProductFormatted>> {
        Timber.v("MLSearchProductUseCase")
        return repository.searchProduct(searchQuery)
    }
}