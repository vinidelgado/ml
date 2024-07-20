package com.vdelgado.ml.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.vdelgado.ml.domain.model.MLProductFormatted
import com.vdelgado.ml.domain.usecase.product.MLSearchProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import androidx.compose.runtime.State
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val searchUserCase: MLSearchProductUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state

    fun searchProductPager(searchQuery: String) {
        val products = searchUserCase.invoke(searchQuery).cachedIn(viewModelScope)
        _state.value = _state.value.copy(products = products)
    }

}


data class SearchState(
    val searchQuery: String = "",
    val products: Flow<PagingData<MLProductFormatted>>? = null
)