package com.luigivampa92.moviereviews.data.rest

import com.luigivampa92.moviereviews.data.rest.model.GetReviewsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface RestService {

    @GET("reviews/search.json")
    fun getReviews(@QueryMap query: Map<String, String>): Observable<GetReviewsResponse>
}