package com.oelnooc.storesinfo.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FrogmiClient {

    companion object {
        private const val BASE_URL = "https://api.frogmi.com/"

        fun getInstance(): FrogmiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(FrogmiService::class.java)
        }
    }
}