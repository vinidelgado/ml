package com.vdelgado.ml.domain.model

data class MLProductFormatted(
    val title: String,
    val originalPrice: String,
    val price: String,
    val freeShipping: Boolean,
    val imageUrl: String,
    val installments: String
)