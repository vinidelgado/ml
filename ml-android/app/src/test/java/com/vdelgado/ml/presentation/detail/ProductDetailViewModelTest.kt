package com.vdelgado.ml.presentation.detail

import com.vdelgado.ml.mock.mockProductItem
import com.vdelgado.ml.domain.model.Result
import com.vdelgado.ml.domain.usecase.product.MLProductItemUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
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
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class ProductDetailViewModelTest {
    private val mlProductItemUseCase: MLProductItemUseCase = mockk(relaxed = true)
    private lateinit var viewModel: ProductDetailViewModel
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

    private fun setupViewModel() {
        viewModel = ProductDetailViewModel(mlProductItemUseCase)
    }

    @Test
    fun `onCreate SHOULD not emit any event WHEN userCase returns Success`() = runTest {
        viewModel.onEvent(ProductDetailViewModel.ProductDetailEvent.UpdateProductProduct("MLB120000"))
        // Given
        coEvery { mlProductItemUseCase(any()) } returns Result.Success.Content(mockProductItem)
        // When
        viewModel.onEvent(ProductDetailViewModel.ProductDetailEvent.GetInfoProduct)
        // Then
        assertEquals(mockProductItem, viewModel.state.value.product)
    }


}