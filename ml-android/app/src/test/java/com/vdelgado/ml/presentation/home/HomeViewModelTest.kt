package com.vdelgado.ml.presentation.home

import com.vdelgado.ml.data.repository.SearchMLProductsPagingSourceImpl
import com.vdelgado.ml.data.remote.common.MLServiceApi
import com.vdelgado.ml.domain.repository.MLSearchProductRepository
import com.vdelgado.ml.domain.usecase.product.MLSearchProductUseCase
import io.mockk.MockKAnnotations
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {
    private val mlSearchProductRepository: MLSearchProductRepository = mockk(relaxed = true)
    private val mlSearchProductUseCase: MLSearchProductUseCase = mockk(relaxed = true)
    private val api: MLServiceApi = mockk(relaxed = true)
    private val searchPagingSource: SearchMLProductsPagingSourceImpl = mockk(relaxed = true)
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
        viewModel.onEvent(HomeViewModel.HomeEvent.UpdateSearchQuery(changeValue))
        assert(viewModel.state.value.searchQuery == "MLB15325457")
    }

    private fun setupViewModel() {
        viewModel = HomeViewModel(mlSearchProductUseCase)
    }
}