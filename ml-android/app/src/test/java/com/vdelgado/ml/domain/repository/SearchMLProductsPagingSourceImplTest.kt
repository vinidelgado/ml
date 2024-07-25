package com.vdelgado.ml.domain.repository

import androidx.paging.PagingSource
import com.vdelgado.ml.common.mockMLProductFormatted
import com.vdelgado.ml.common.mockMLSearchProductResponse
import com.vdelgado.ml.data.remote.NoNetworkException
import com.vdelgado.ml.data.repository.SearchMLProductsPagingSourceImpl
import com.vdelgado.ml.data.remote.common.MLServiceApi
import com.vdelgado.ml.data.remote.data.MLSearchProductResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okio.IOException
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchMLProductsPagingSourceImplTest {
    @get:Rule
    private val api: MLServiceApi = mockk(relaxed = true)
    private lateinit var searchMLProductsPagingSource: SearchMLProductsPagingSourceImpl
    private val dispatcher = UnconfinedTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        MockKAnnotations.init()
        setupViewModel()
        Dispatchers.setMain(dispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `paging source load success received with api data`() = runTest {
        coEvery {
            api.searchProduct(
                any(),
                any(),
                any(),
                any()
            )
        } returns mockMLSearchProductResponse

        val expectedResult = PagingSource.LoadResult.Page(
            data = listOf(mockMLProductFormatted),
            prevKey = null,
            nextKey = 1
        )

        val result = searchMLProductsPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = 0,
                loadSize = 1,
                placeholdersEnabled = false
            )
        )
        assertEquals(
            expectedResult, result
        )
    }

    @Test
    fun `paging source load failure received network exception`() = runTest {
        val error = NoNetworkException("Network Error")
        coEvery { api.searchProduct(any(), any(), any(), any()) } throws error

        val expectedResult =
            PagingSource.LoadResult.Error<Int, MLSearchProductResponse>(throwable = error)

        val result = searchMLProductsPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = 0,
                loadSize = 1,
                placeholdersEnabled = false
            )
        )
        assertEquals(
            expectedResult, result
        )
    }

    @Test
    fun `paging source load failure received io exception`() = runTest {
        val error = IOException()
        coEvery { api.searchProduct(any(), any(), any(), any()) } throws error

        val expectedResult =
            PagingSource.LoadResult.Error<Int, MLSearchProductResponse>(throwable = error)

        val result = searchMLProductsPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = 0,
                loadSize = 1,
                placeholdersEnabled = false
            )
        )
        assertEquals(
            expectedResult, result
        )
    }

    @Test
    fun `paging source load failure received default exception`() = runTest {
        val error = Exception()
        coEvery { api.searchProduct(any(), any(), any(), any()) } throws error

        val expectedResult =
            PagingSource.LoadResult.Error<Int, MLSearchProductResponse>(throwable = error)

        val result = searchMLProductsPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = 0,
                loadSize = 1,
                placeholdersEnabled = false
            )
        )
        assertEquals(
            expectedResult, result
        )
    }


    private fun setupViewModel() {
        searchMLProductsPagingSource = SearchMLProductsPagingSourceImpl(api, "Teste")
    }
}