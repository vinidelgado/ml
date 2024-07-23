package com.vdelgado.ml.domain.model

class MLProductItem(
    val condition: String,
    val title: String,
    val itemId: String,
    val originalPrice: String,
    val price: String,
    val permalink:String,
    val freeShipping: Boolean,
    val quantity: Int,
    val pictures: List<String>,
    val warranty: String,
//    val installments: String
)