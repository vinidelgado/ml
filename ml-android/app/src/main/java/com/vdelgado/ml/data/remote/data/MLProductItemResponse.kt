package com.vdelgado.ml.data.remote.data

import com.google.gson.annotations.SerializedName

data class MLProductItemResponse(
    @SerializedName("id") val id: String,
    @SerializedName("site_id") val siteId: String,
    @SerializedName("title") val title: String,
    @SerializedName("price") val price: Double,
    @SerializedName("original_price") val originalPrice: Double?,
    @SerializedName("currency_id") val currencyId: String,
    @SerializedName("initial_quantity") val initialQuantity: Int,
    @SerializedName("condition") val condition: String,
    @SerializedName("warranty") val warranty: String?,
    @SerializedName("permalink") val permalink: String,
    @SerializedName("pictures") val pictures: List<MLProductItemPictureResponse>,
    @SerializedName("shipping") val shipping: MLProductItemShippingResponse?,
)

data class MLProductItemShippingResponse(
    @SerializedName("store_pick_up") val storePickUp: Boolean,
    @SerializedName("free_shipping") val freeShipping: Boolean,
    @SerializedName("logistic_type") val logisticType: String,
    @SerializedName("mode") val mode: String,
    @SerializedName("tags") val tags: List<String>,
    @SerializedName("methods") val methods: List<String>,
    @SerializedName("dimensions") val dimensions: String?,
    @SerializedName("local_pick_up") val localPickUp: Boolean
)

data class MLProductItemPictureResponse(
    @SerializedName("id") val id: String,
    @SerializedName("url") val url: String,
    @SerializedName("secure_url") val secureUrl: String,
    @SerializedName("size") val size: String,
    @SerializedName("max_size") val maxSize: String,
    @SerializedName("quality") val quality: String
)

