package com.vdelgado.ml.ui

import androidx.paging.PagingSource
import com.vdelgado.ml.data.remote.NoNetworkException
import com.vdelgado.ml.data.remote.SearchMLProductsPagingSource
import com.vdelgado.ml.data.remote.common.MLServiceApi
import com.vdelgado.ml.data.remote.data.MLSearchProductResponse
import com.vdelgado.ml.domain.repository.MLSearchProductRepository
import com.vdelgado.ml.domain.usecase.product.MLSearchProductUseCase
import com.vdelgado.ml.presentation.home.HomeEvent
import com.vdelgado.ml.presentation.home.HomeViewModel
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
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {
    @get:Rule
    private val mlSearchProductRepository: MLSearchProductRepository = mockk(relaxed = true)
    private val mlSearchProductUseCase: MLSearchProductUseCase = mockk(relaxed = true)
    private val api: MLServiceApi = mockk(relaxed = true)
    private val searchPagingSource: SearchMLProductsPagingSource = mockk(relaxed = true)
    private lateinit var viewModel: HomeViewModel
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
    fun `onEvent UpdateSearchQuery update variable state`() = runTest {
        setupViewModel()
        val changeValue = "MLB15325457"
        viewModel.onEvent(HomeEvent.UpdateSearchQuery(changeValue))
        assert(viewModel.state.value.searchQuery == "MLB15325457")
    }

    @Test
    fun `paging source load failure received io exception`() = runTest {
        val error = NoNetworkException("Network Error")
        coEvery { api.searchProduct(any(), any(), any(), any()) } throws error

        val expectedResult =
            PagingSource.LoadResult.Error<Int, MLSearchProductResponse>(throwable = error)

        val result = searchPagingSource.load(
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
        viewModel = HomeViewModel(mlSearchProductUseCase)
    }
}