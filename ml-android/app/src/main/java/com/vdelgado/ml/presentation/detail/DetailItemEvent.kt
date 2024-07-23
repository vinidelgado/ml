package com.vdelgado.ml.presentation.detail

sealed class DetailItemEvent {
    data class UpdateProductItem(val productItem: String) : DetailItemEvent()
    data object GetInfoProduct : DetailItemEvent()
}