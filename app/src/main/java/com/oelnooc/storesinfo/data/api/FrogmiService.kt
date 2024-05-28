package com.oelnooc.storesinfo.data.api

import com.oelnooc.storesinfo.data.model.FrogmiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Url

interface FrogmiService {

    @GET("api/v3/stores?per_page=10")
    @Headers("Content-Type: application/vnd.api+json")
    fun getFirstPage(
        @Header("Authorization") token: String,
        @Header("X-Company-UUID") companyUUID: String
    ): Call<FrogmiResponse>

    @GET
    @Headers("Content-Type: application/vnd.api+json")
    fun getNextPage(
        @Header("Authorization") token: String,
        @Header("X-Company-UUID") companyUUID: String,
        @Url nextPageUrl: String
    ): Call<FrogmiResponse>
}