package com.luigivampa92.moviereviews.data.rest.model

import com.google.gson.annotations.SerializedName

data class ReviewMultimediaModel constructor (
        @SerializedName("type") var type: String,
        @SerializedName("src") var src: String,
        @SerializedName("width") var width: Int,
        @SerializedName("height") var height: Int
)