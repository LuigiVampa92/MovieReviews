package com.luigivampa92.moviereviews.data.rest.model

import com.google.gson.annotations.SerializedName

data class ReviewContentModel constructor (
        @SerializedName("display_title") var displayTitle: String,
        @SerializedName("byline") var byline: String,
        @SerializedName("headline") var headline: String,
        @SerializedName("summary_short") var summaryShort: String,
        @SerializedName("publication_date") var publicationDate: String?,
        @SerializedName("opening_date") var openingDate: String?,
        @SerializedName("date_updated") var updatedDate: String,
        @SerializedName("link") var link: ReviewLinkModel,
        @SerializedName("multimedia") var multimedia: ReviewMultimediaModel?,
        @SerializedName("critics_pick") var criticsPick: Int,
        @SerializedName("mpaa_rating") var rating: String
)