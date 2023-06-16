package com.sample.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.sample.data.entities.RelatedTopic
import com.sample.data.entities.TvShowData
import com.sample.networking.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object Repository {
    fun getCharacters(show: String, liveData: MutableLiveData<ArrayList<RelatedTopic>>?) {
        RetrofitClient.getService().getTvShowData(show).enqueue(object : Callback<TvShowData>{
            override fun onResponse(call: Call<TvShowData>?, response: Response<TvShowData>?) {
                if(response?.body() != null && response.isSuccessful) {
                    liveData?.value = response.body()?.relatedTopics as ArrayList<RelatedTopic>
                }
            }

            override fun onFailure(call: Call<TvShowData>?, t: Throwable?) {
                // error handling
                Log.e("Repository",t?.message.toString())
            }
        })
    }
}