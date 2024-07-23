package com.vdelgado.ml.data.remote.data

import com.google.gson.annotations.SerializedName

data class MLProductItemResponse(
    @SerializedName("id") val id: String,
    @SerializedName("site_id") val siteId: String,
    @SerializedName("title") val title: String,
//    @SerializedName("seller_id") val sellerId: Int,
//    @SerializedName("category_id") val categoryId: String,
//    @SerializedName("official_store_id") val officialStoreId: Int?,
    @SerializedName("price") val price: Double,
//    @SerializedName("base_price") val basePrice: Double,
    @SerializedName("original_price") val originalPrice: Double?,
    @SerializedName("currency_id") val currencyId: String,
    @SerializedName("initial_quantity") val initialQuantity: Int,
//    @SerializedName("sale_terms") val saleTerms: List<MLProductItemSaleTermResponse>,
//    @SerializedName("buying_mode") val buyingMode: String,
//    @SerializedName("listing_type_id") val listingTypeId: String,
    @SerializedName("condition") val condition: String,
    @SerializedName("warranty") val warranty: String?,
//    @SerializedName("permalink") val permalink: String,
//    @SerializedName("thumbnail_id") val thumbnailId: String,
//    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("pictures") val pictures: List<MLProductItemPictureResponse>,
//    @SerializedName("video_id") val videoId: String?,
//    @SerializedName("descriptions") val descriptions: List<String>,
//    @SerializedName("accepts_mercadopago") val acceptsMercadopago: Boolean,
//    @SerializedName("non_mercado_pago_payment_methods") val nonMercadoPagoPaymentMethods: List<String>,
    @SerializedName("shipping") val shipping: MLProductItemShippingResponse?,
//    @SerializedName("international_delivery_mode") val internationalDeliveryMode: String,
//    @SerializedName("seller_address") val sellerAddress: MLProductItemSellerAddressResponse,
//    @SerializedName("seller_contact") val sellerContact: String?,
//    @SerializedName("coverage_areas") val coverageAreas: List<String>,
//    @SerializedName("attributes") val attributes: List<MLProductItemAttributeResponse>
)

data class MLProductItemSaleTermResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("value_id") val valueId: String?,
    @SerializedName("value_name") val valueName: String,
    @SerializedName("value_struct") val valueStruct: MLValueStruct?,
    @SerializedName("values") val values: List<MLValue>,
    @SerializedName("value_type") val valueType: String
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

data class MLProductItemValueStructResponse(
    @SerializedName("number") val number: Int,
    @SerializedName("unit") val unit: String
)

data class MLProductItemValueResponse(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String,
    @SerializedName("struct") val struct: MLValueStruct?
)

data class MLProductItemPictureResponse(
    @SerializedName("id") val id: String,
    @SerializedName("url") val url: String,
    @SerializedName("secure_url") val secureUrl: String,
    @SerializedName("size") val size: String,
    @SerializedName("max_size") val maxSize: String,
    @SerializedName("quality") val quality: String
)

data class MLProductItemSellerAddressResponse(
    @SerializedName("city") val city: MLProductItemLocationInfoResponse,
    @SerializedName("state") val state: MLProductItemLocationInfoResponse,
    @SerializedName("country") val country: MLProductItemLocationInfoResponse,
    @SerializedName("search_location") val searchLocation: MLProductItemLocationInfoResponse,
    @SerializedName("id") val id: Int
)

data class MLProductItemLocationInfoResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String
)

data class MLProductItemSearchLocationResponse(
    @SerializedName("neighborhood") val neighborhood: MLProductItemLocationInfoResponse,
    @SerializedName("city") val city: MLProductItemLocationInfoResponse,
    @SerializedName("state") val state: MLProductItemLocationInfoResponse
)

data class MLProductItemAttributeResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("value_id") val valueId: String?,
    @SerializedName("value_name") val valueName: String,
    @SerializedName("values") val values: List<MLValue>,
    @SerializedName("value_type") val valueType: String
)
