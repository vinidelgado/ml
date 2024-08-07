package com.vdelgado.ml.presentation.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vdelgado.ml.domain.model.MLProductItem
import com.vdelgado.ml.domain.model.Result
import com.vdelgado.ml.domain.usecase.product.MLProductItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val itemProductUseCase: MLProductItemUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(ProductDetailState())
    val state: State<ProductDetailState> = _state

    data class ProductDetailState(
        val productItem: String = "",
        val product: MLProductItem? = null,
        val isError: Boolean = false,
        val isNetworkError: Boolean = false
    )

    sealed class ProductDetailEvent {
        data class UpdateProductProduct(val productItem: String) : ProductDetailEvent()
        data object GetInfoProduct : ProductDetailEvent()
    }

    fun onEvent(event: ProductDetailEvent) {
        when (event) {
            is ProductDetailEvent.GetInfoProduct -> {
                getProductDetail()
            }

            is ProductDetailEvent.UpdateProductProduct -> {
                _state.value = state.value.copy(
                    productItem = event.productItem
                )
            }

            else -> {
                Timber.e("Event not handled")
            }
        }
    }

    private fun getProductDetail() {
        viewModelScope.launch {
            when (val result = itemProductUseCase.invoke(state.value.productItem)) {
                is Result.Success.Content -> {
                    _state.value = _state.value.copy(product = result.data,isError = false)
                }

                is Result.Success.Error -> {
                    _state.value = _state.value.copy(isError = true, isNetworkError = true)
                }

                is Result.Failure -> {
                    _state.value = _state.value.copy(isError = true, isNetworkError = false)
                }
            }

        }
    }

}