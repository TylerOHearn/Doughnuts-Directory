package com.sample.data.entities


import com.google.gson.annotations.SerializedName

data class TvShowData(
    @SerializedName("Heading")
    val heading: String,
    @SerializedName("RelatedTopics")
    val characters: List<Characters>
)