package com.sample.networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {

    fun getClient() : Retrofit = Retrofit.Builder()
        .baseUrl("https://api.duckduckgo.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getService(): DuckDuckGoService {
        return getClient().create(DuckDuckGoService::class.java)
    }
}