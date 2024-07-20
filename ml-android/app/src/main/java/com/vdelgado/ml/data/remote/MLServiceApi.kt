package com.vdelgado.ml.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MLServiceApi {
    @GET("/sites/{site_id}/search")
    suspend fun searchProduct(
        @Path("site_id") siteId: String = "MLB",
        @Query("q") query: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): MLSearchProductResponse
}