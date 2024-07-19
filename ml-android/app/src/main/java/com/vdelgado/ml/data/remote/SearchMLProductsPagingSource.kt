package com.vdelgado.ml.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vdelgado.ml.domain.model.MLProduct

class SearchMLProductsPagingSource(
    private val api: MLServiceApi,
    private val searchQuery: String,
) : PagingSource<Int, MLProduct>() {

    private var totalProductCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MLProduct> {
        val page = params.key ?: 1
        return try {
            val apiResponse = api.searchProduct(
                query = searchQuery,
                offset = page,
                limit = 10
            )

            totalProductCount += apiResponse.results.size

            val products = apiResponse.results.distinctBy { it.id }

            LoadResult.Page(
                data = products,
                nextKey = if (totalProductCount == apiResponse.paging.total) {
                    null
                } else {
                    page + 1
                },
                prevKey = null
            )
        } catch (ex: Exception) {
            ex.printStackTrace()
            LoadResult.Error(throwable = ex)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MLProduct>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}