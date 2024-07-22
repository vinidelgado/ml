package com.vdelgado.ml.presentation.navigation.home

sealed class HomeEvent {
    data object SearchProduct : HomeEvent()
    data class UpdateSearchQuery(val searchQuery: String) : HomeEvent()
}