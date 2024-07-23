package com.vdelgado.ml.presentation.home

sealed class HomeEvent {
    data object SearchProduct : HomeEvent()
    data class UpdateSearchQuery(val searchQuery: String) : HomeEvent()
}