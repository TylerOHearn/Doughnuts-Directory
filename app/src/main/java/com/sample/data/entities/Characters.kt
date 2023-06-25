package com.sample.data.entities


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Characters(
    @SerializedName("FirstURL")
    val firstURL: String,
    @SerializedName("Icon")
    val icon: Icon,
    @SerializedName("Text")
    val text: String
) : Parcelable