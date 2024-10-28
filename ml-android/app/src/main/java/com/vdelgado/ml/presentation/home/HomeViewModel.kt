package com.vdelgado.ml.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.vdelgado.ml.domain.model.MLProductScreenFormatted
import com.vdelgado.ml.domain.usecase.product.MLSearchProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val searchUserCase: MLSearchProductUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    data class HomeState(
        val searchQuery: String = "Apple Watch S8 45mm",
        val products: Flow<PagingData<MLProductScreenFormatted>>? = null
    )

    sealed class HomeEvent {
        data object SearchProduct : HomeEvent()
        data class UpdateSearchQuery(val searchQuery: String) : HomeEvent()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.UpdateSearchQuery -> {
                Timber.v("Call HomeEvent.UpdateSearchQuery")
                _state.value = state.value.copy(
                    searchQuery = event.searchQuery
                )
            }

            is HomeEvent.SearchProduct -> {
                Timber.v("Call HomeEvent.SearchProduct")
                searchProductPager()
            }
        }
    }

    private fun searchProductPager() {
        Timber.v("Call HomeViewModel searchProductPager")
        val products = searchUserCase.invoke(state.value.searchQuery).cachedIn(viewModelScope)
        _state.value = _state.value.copy(products = products)
    }

}