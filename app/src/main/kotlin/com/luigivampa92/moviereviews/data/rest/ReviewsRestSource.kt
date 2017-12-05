package com.luigivampa92.moviereviews.data.rest

import android.content.res.Resources
import com.luigivampa92.moviereviews.R
import com.luigivampa92.moviereviews.data.rest.model.ReviewContentModel
import com.luigivampa92.moviereviews.domain.Review
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReviewsRestSource @Inject constructor (val restService: RestService, val resources: Resources) {

    companion object {
        private const val PARAM_API_KEY = "api-key"
        private const val PARAM_QUERY = "query"
        private const val PARAM_OFFSET = "offset"
    }

    fun getReviews(query: String?, offset: Int): Observable<List<Review>> {
        return restService.getReviews(getRequestParameters(query, offset))
                .flatMapIterable({ response -> response.results })
                .map({ model -> map(model) })
                .toList()
                .toObservable()
    }

    private fun getRequestParameters(query: String?, offset: Int): Map<String, String> {
        val parameters = mutableMapOf<String, String>(
                PARAM_API_KEY to resources.getString(R.string.api_key),
                PARAM_OFFSET to offset.toString()
        )
        query?.let { if (query.isNotEmpty()) parameters[PARAM_QUERY] = query }
        return parameters
    }

    private fun map(model: ReviewContentModel): Review {
        return Review(
                model.displayTitle,
                model.byline,
                model.headline,
                model.summaryShort,
                model.publicationDate,
                model.openingDate,
                model.updatedDate,
                model.link.type,
                model.link.url,
                model.link.suggestedLinkText,
                model.multimedia?.type,
                model.multimedia?.src,
                model.multimedia?.width,
                model.multimedia?.height,
                model.criticsPick,
                model.rating
        )
    }
}