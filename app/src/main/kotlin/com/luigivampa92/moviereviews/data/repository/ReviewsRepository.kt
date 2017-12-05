package com.luigivampa92.moviereviews.data.repository

import com.luigivampa92.moviereviews.data.rest.ReviewsRestSource
import com.luigivampa92.moviereviews.domain.Review
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReviewsRepository @Inject constructor(val restSource: ReviewsRestSource) {

    fun getReviews(query: String?, offset: Int): Observable<List<Review>> {
        return restSource.getReviews(query, offset)
    }
}