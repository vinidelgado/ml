package com.vdelgado.ml.presentation.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vdelgado.ml.data.remote.data.MLProductItemResponse
import com.vdelgado.ml.domain.usecase.product.MLItemProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val itemProductUseCase: MLItemProductUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(DetailState())
    val state: State<DetailState> = _state

    fun onEvent(event: DetailItemEvent) {
        when (event) {
            is DetailItemEvent.GetInfoProduct -> {
                getProductDetail()
            }

            is DetailItemEvent.UpdateProductItem -> {
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
            val result = itemProductUseCase.invoke(state.value.productItem)
            _state.value = _state.value.copy(product = result)
        }
    }

}

data class DetailState(
    val productItem: String = "",
    val product: MLProductItemResponse? = null
)