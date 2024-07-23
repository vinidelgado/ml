package com.vdelgado.ml.data.remote.data

import com.google.gson.annotations.SerializedName

data class MLSearchProductResponse(
    @SerializedName("site_id") val siteId: String,
    @SerializedName("country_default_time_zone") val countryDefaultTimeZone: String,
    @SerializedName("query") val query: String,
    @SerializedName("paging") val paging: MLPagingResponse,
    @SerializedName("results") val results: List<MLProductResponse>,
    @SerializedName("sort") val sort: MLSortResponse,
    @SerializedName("available_sorts") val availableSorts: List<MLSortResponse>,
    @SerializedName("filters") val filters: List<MLFilterResponse>,
    @SerializedName("available_filters") val availableFilters: List<MLAvailableFilter>
)

data class MLPagingResponse(
    @SerializedName("total") val total: Int,
    @SerializedName("primary_results") val primaryResults: Int,
    @SerializedName("offset") val offset: Int,
    @SerializedName("limit") val limit: Int
)

data class MLProductResponse(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("condition") val condition: String,
    @SerializedName("thumbnail_id") val thumbnailId: String,
//    @SerializedName("catalog_product_id") val catalogProductId: String?,
//    @SerializedName("listing_type_id") val listingTypeId: String?,
//    @SerializedName("sanitized_title") val sanitizedTitle: String?,
//    @SerializedName("permalink") val permalink: String?,
//    @SerializedName("buying_mode") val buyingMode: String?,
//    @SerializedName("site_id") val siteId: String?,
//    @SerializedName("category_id") val categoryId: String?,
//    @SerializedName("domain_id") val domainId: String?,
    @SerializedName("thumbnail") val thumbnail: String?,
    @SerializedName("currency_id") val currencyId: String?,
//    @SerializedName("order_backend") val orderBackend: Int?,
    @SerializedName("price") val price: Double?,
    @SerializedName("original_price") val originalPrice: Double?,
    @SerializedName("sale_price") val salePrice: Double?,
    @SerializedName("available_quantity") val availableQuantity: Int,
//    @SerializedName("official_store_id") val officialStoreId: Int?,
//    @SerializedName("use_thumbnail_id") val useThumbnailId: Boolean?,
//    @SerializedName("accepts_mercadopago") val acceptsMercadoPago: Boolean?,
    @SerializedName("shipping") val shipping: MLShippingResponse?,
//    @SerializedName("stop_time") val stopTime: String?,
//    @SerializedName("seller") val seller: MLSeller?,
//    @SerializedName("attributes") val attributes: List<MLAttributes>?,
    @SerializedName("installments") val installments: MLInstallmentsResponse?,
//    @SerializedName("winner_item_id") val winnerItemId: String?,
//    @SerializedName("catalog_listing") val catalogListing: Boolean?,
//    @SerializedName("discounts") val discounts: List<Any>?,
//    @SerializedName("promotions") val promotions: List<Any>?,
//    @SerializedName("differential_pricing") val differentialPricing: MLDifferentialPricing?,
//    @SerializedName("inventory_id") val inventoryId: String?
)

data class MLShippingResponse(
    @SerializedName("store_pick_up") val storePickUp: Boolean,
    @SerializedName("free_shipping") val freeShipping: Boolean,
    @SerializedName("logistic_type") val logisticType: String,
    @SerializedName("mode") val mode: String,
    @SerializedName("tags") val tags: List<String>,
    @SerializedName("benefits") val benefits: String?,
    @SerializedName("promise") val promise: String?,
    @SerializedName("shipping_score") val shippingScore: Int
)

data class MLSeller(
    @SerializedName("id") val id: Int,
    @SerializedName("nickname") val nickname: String
)

data class MLAttributes(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("value_id") val valueId: String?,
    @SerializedName("value_name") val valueName: String,
    @SerializedName("attribute_group_id") val attributeGroupId: String,
    @SerializedName("attribute_group_name") val attributeGroupName: String,
    @SerializedName("value_struct") val valueStruct: MLValueStruct?,
    @SerializedName("values") val values: List<MLValue>,
    @SerializedName("source") val source: Int,
    @SerializedName("value_type") val valueType: String
)

data class MLValueStruct(
    @SerializedName("number") val number: Double,
    @SerializedName("unit") val unit: String
)

data class MLValue(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String,
    @SerializedName("struct") val struct: MLValueStruct?,
    @SerializedName("source") val source: Int
)

data class MLInstallmentsResponse(
    @SerializedName("quantity") val quantity: Int,
    @SerializedName("amount") val amount: Double,
    @SerializedName("rate") val rate: Double,
    @SerializedName("currency_id") val currencyId: String
)

data class MLDifferentialPricing(
    @SerializedName("id") val id: Int
)

data class MLSortResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String
)

data class MLFilterResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("type") val type: String,
    @SerializedName("values") val values: List<FilterValue>
)

data class FilterValue(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("path_from_root") val pathFromRoot: List<PathFromRoot>?
)

data class PathFromRoot(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String
)

data class MLAvailableFilter(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("type") val type: String,
    @SerializedName("values") val values: List<AvailableFilterValue>
)

data class AvailableFilterValue(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("results") val results: Int
)

