package com.luigivampa92.moviereviews.data.rest.model

import com.google.gson.annotations.SerializedName

data class ReviewLinkModel constructor (
        @SerializedName("type") var type: String,
        @SerializedName("url") var url: String,
        @SerializedName("suggested_link_text") var suggestedLinkText: String
        )