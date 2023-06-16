package com.sample.networking

import android.R.attr.value
import com.sample.data.entities.TvShowData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface DuckDuckGoService {
    @GET("/")
    fun getTvShowData(@Query(value = "q", encoded=true) q:String?, @Query("format") format: String = "json"): Call<TvShowData>
}