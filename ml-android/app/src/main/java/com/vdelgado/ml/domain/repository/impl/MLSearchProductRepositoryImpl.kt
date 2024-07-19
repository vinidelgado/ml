package com.vdelgado.ml.domain.repository.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.vdelgado.ml.domain.model.MLProduct
import com.vdelgado.ml.data.remote.MLServiceApi
import com.vdelgado.ml.data.remote.SearchMLProductsPagingSource
import com.vdelgado.ml.domain.repository.MLSearchProductRepository
import kotlinx.coroutines.flow.Flow

class MLSearchProductRepositoryImpl(private val mlServiceApi: MLServiceApi) :
    MLSearchProductRepository {
    override fun searchProduct(searchQuery: String): Flow<PagingData<MLProduct>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                SearchMLProductsPagingSource(mlServiceApi, searchQuery)
            }).flow
    }
}