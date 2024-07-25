package com.vdelgado.ml.common

import com.vdelgado.ml.data.remote.data.AvailableFilterValue
import com.vdelgado.ml.data.remote.data.FilterValue
import com.vdelgado.ml.data.remote.data.MLAvailableFilter
import com.vdelgado.ml.data.remote.data.MLFilterResponse
import com.vdelgado.ml.data.remote.data.MLInstallmentsResponse
import com.vdelgado.ml.data.remote.data.MLPagingResponse
import com.vdelgado.ml.data.remote.data.MLProductItemPictureResponse
import com.vdelgado.ml.data.remote.data.MLProductItemResponse
import com.vdelgado.ml.data.remote.data.MLProductItemShippingResponse
import com.vdelgado.ml.data.remote.data.MLProductResponse
import com.vdelgado.ml.data.remote.data.MLSearchProductResponse
import com.vdelgado.ml.data.remote.data.MLShippingResponse
import com.vdelgado.ml.data.remote.data.MLSortResponse
import com.vdelgado.ml.data.remote.data.PathFromRoot
import com.vdelgado.ml.domain.model.MLProductFormatted
import com.vdelgado.ml.domain.model.MLProductItem

val mockMLProductFormatted = MLProductFormatted(
    title = "Smartphone XYZ",
    originalPrice = "\$64,999.99",
    price = "\$59,999.99",
    freeShipping = false,
    imageUrl = "http://example.com/thumbnail.jpg",
    installments = "em 12x \$4,999.99 sem juros",
    itemId = "MLA123456"
)

val mockMLSearchProductResponse = MLSearchProductResponse(
    siteId = "MLA",
    countryDefaultTimeZone = "GMT-03:00",
    query = "smartphone",
    paging = MLPagingResponse(
        total = 1000,
        primaryResults = 100,
        offset = 0,
        limit = 50
    ),
    results = listOf(
        MLProductResponse(
            id = "MLA123456",
            title = "Smartphone XYZ",
            condition = "new",
            thumbnailId = "123abc",
            thumbnail = "http://example.com/thumbnail.jpg",
            currencyId = "ARS",
            price = 59999.99,
            originalPrice = 64999.99,
            salePrice = 54999.99,
            availableQuantity = 20,
            shipping = MLShippingResponse(
                storePickUp = true,
                freeShipping = false,
                logisticType = "fulfillment",
                mode = "me2",
                tags = listOf("self_service_in"),
                benefits = "10% off",
                promise = "next day",
                shippingScore = 90
            ),
            installments = MLInstallmentsResponse(
                quantity = 12,
                amount = 4999.99,
                rate = 0.0,
                currencyId = "ARS"
            )
        )
    ),
    sort = MLSortResponse(
        id = "relevance",
        name = "Relevance"
    ),
    availableSorts = listOf(
        MLSortResponse(
            id = "price_asc",
            name = "Price: Low to High"
        ),
        MLSortResponse(
            id = "price_desc",
            name = "Price: High to Low"
        )
    ),
    filters = listOf(
        MLFilterResponse(
            id = "category",
            name = "Category",
            type = "text",
            values = listOf(
                FilterValue(
                    id = "MLA1055",
                    name = "Smartphones",
                    pathFromRoot = listOf(
                        PathFromRoot(
                            id = "MLA1000",
                            name = "Electronics"
                        ),
                        PathFromRoot(
                            id = "MLA1055",
                            name = "Smartphones"
                        )
                    )
                )
            )
        )
    ),
    availableFilters = listOf(
        MLAvailableFilter(
            id = "brand",
            name = "Brand",
            type = "text",
            values = listOf(
                AvailableFilterValue(
                    id = "Samsung",
                    name = "Samsung",
                    results = 500
                ),
                AvailableFilterValue(
                    id = "Apple",
                    name = "Apple",
                    results = 300
                )
            )
        )
    )
)

val mockProductItem = MLProductItem(
    condition = "Novo",
    title = "Smartphone XYZ",
    itemId = "123456789",
    originalPrice = "R$ 2000,00",
    price = "R$ 1500,00",
    permalink = "https://www.exemplo.com/produto/123456789",
    freeShipping = true,
    quantity = 10,
    pictures = listOf(
        "https://www.exemplo.com/imagem1.jpg",
        "https://www.exemplo.com/imagem2.jpg"
    ),
    warranty = "1 ano de garantia"
)

val mockMLProductItemResponse = MLProductItemResponse(
    id = "12345",
    siteId = "MLA",
    title = "Sample Product",
    price = 999.99,
    originalPrice = 1299.99,
    currencyId = "USD",
    initialQuantity = 100,
    condition = "new",
    warranty = "1 year",
    permalink = "https://www.example.com/product/12345",
    pictures = listOf(
        MLProductItemPictureResponse(
            id = "pic1",
            url = "https://www.example.com/images/pic1.jpg",
            secureUrl = "https://www.example.com/images/pic1_secure.jpg",
            size = "500x500",
            maxSize = "1000x1000",
            quality = "high"
        ),
        MLProductItemPictureResponse(
            id = "pic2",
            url = "https://www.example.com/images/pic2.jpg",
            secureUrl = "https://www.example.com/images/pic2_secure.jpg",
            size = "500x500",
            maxSize = "1000x1000",
            quality = "high"
        )
    ),
    shipping = MLProductItemShippingResponse(
        storePickUp = true,
        freeShipping = true,
        logisticType = "cross_docking",
        mode = "me2",
        tags = listOf("standard_shipping", "quick_delivery"),
        methods = listOf("UPS", "FedEx"),
        dimensions = "10x10x10",
        localPickUp = false
    )
)

val mockMLProductItem = MLProductItem(
    condition = "new",
    title = "Sample Product",
    itemId = "12345",
    originalPrice = "1299.99",
    price = "999.99",
    permalink = "https://www.example.com/product/12345",
    freeShipping = true,
    quantity = 100,
    pictures = listOf(
        "https://www.example.com/images/pic1.jpg",
        "https://www.example.com/images/pic2.jpg"
    ),
    warranty = "1 year"
)

