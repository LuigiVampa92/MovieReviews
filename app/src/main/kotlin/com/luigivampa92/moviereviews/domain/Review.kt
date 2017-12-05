package com.luigivampa92.moviereviews.domain

data class Review constructor (
        var displayTitle: String,
        var byline: String,
        var headline: String,
        var summaryShort: String,
        var publicationDate: String?,
        var openingDate: String?,
        var updatedDate: String,
        var linkType: String,
        var linkUrl: String,
        var linkSuggestedText: String,
        var mediaType: String?,
        var mediaSrc: String?,
        var mediaWidth: Int?,
        var mediaHeight: Int?,
        var criticsPick: Int?,
        var rating: String?
)