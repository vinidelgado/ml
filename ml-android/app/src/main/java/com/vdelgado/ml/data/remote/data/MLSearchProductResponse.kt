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
    @SerializedName("thumbnail") val thumbnail: String?,
    @SerializedName("currency_id") val currencyId: String?,
    @SerializedName("price") val price: Double?,
    @SerializedName("original_price") val originalPrice: Double?,
    @SerializedName("sale_price") val salePrice: MLSalePriceResponse,
    @SerializedName("available_quantity") val availableQuantity: Int,
    @SerializedName("shipping") val shipping: MLShippingResponse?,
    @SerializedName("installments") val installments: MLInstallmentsResponse?,
)

data class MLSalePriceResponse(
    @SerializedName("price_id") val priceId: String,
    @SerializedName("amount") val amount: Double,
    @SerializedName("conditions") val conditions: Map<String, Any>,
    @SerializedName("currency_id") val currencyId: String,
    @SerializedName("exchange_rate") val exchangeRate: Double? = null,
    @SerializedName("payment_method_prices") val paymentMethodPrices: List<Any> = emptyList(),
    @SerializedName("payment_method_type") val paymentMethodType: String = "TMP",
    @SerializedName("regular_amount") val regularAmount: Double? = null,
    @SerializedName("type") val type: String = "standard",
    @SerializedName("metadata") val metadata: Map<String, Any> = emptyMap()
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

