package com.vdelgado.ml.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.vdelgado.ml.domain.model.MLProduct
import com.vdelgado.ml.domain.usecase.product.MLSearchProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val searchUserCase: MLSearchProductUseCase,
) : ViewModel() {

    fun searchProductPager(searchQuery:String): Flow<PagingData<MLProduct>> {
        return searchUserCase.invoke(searchQuery).cachedIn(viewModelScope)
    }

}