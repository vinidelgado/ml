package com.vdelgado.ml.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MLProductScreenFormatted(
    val itemId: String,
    val title: String,
    val originalPrice: String,
    val price: String,
    val freeShipping: Boolean,
    val imageUrl: String,
    val installments: String,
    val officialStore:String,
): Parcelable