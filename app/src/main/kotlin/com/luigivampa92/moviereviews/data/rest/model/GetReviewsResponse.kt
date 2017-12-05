package com.luigivampa92.moviereviews.data.rest.model

import com.google.gson.annotations.SerializedName

data class GetReviewsResponse constructor (
        @SerializedName("status") var status: String,
        @SerializedName("copyright") var copyright: String,
        @SerializedName("has_more") var hasMore: Boolean,
        @SerializedName("num_results") var numResults: Int,
        @SerializedName("results") var results: List<ReviewContentModel>
)