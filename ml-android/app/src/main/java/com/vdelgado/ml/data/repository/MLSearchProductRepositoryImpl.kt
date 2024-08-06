package com.vdelgado.ml.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.vdelgado.ml.data.remote.common.MLServiceApi
import com.vdelgado.ml.domain.model.MLProductFormatted
import com.vdelgado.ml.domain.repository.MLSearchProductRepository
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

class MLSearchProductRepositoryImpl(private val mlServiceApi: MLServiceApi) :
    MLSearchProductRepository {
    override fun searchProduct(searchQuery: String): Flow<PagingData<MLProductFormatted>> {
        Timber.v("MLSearchProductRepositoryImpl - searchProduct")
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                SearchMLProductsPagingSourceImpl(mlServiceApi, searchQuery)
            }).flow
    }
}