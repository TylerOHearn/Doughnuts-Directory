package com.sample.data.entities


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class Icon(
    @SerializedName("URL")
    val uRL: String
) : Parcelable