package com.vdelgado.ml.domain.repository

import androidx.paging.PagingData
import com.vdelgado.ml.domain.model.MLProductScreenFormatted
import kotlinx.coroutines.flow.Flow

interface MLSearchProductRepository {
    fun searchProduct(searchQuery: String): Flow<PagingData<MLProductScreenFormatted>>
}