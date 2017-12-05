package com.luigivampa92.moviereviews.mvp

import com.luigivampa92.moviereviews.domain.Review
import com.luigivampa92.moviereviews.mvp.base.BaseView

interface MainView : BaseView {

    fun displayReviews(reviews: List<Review>)

    fun toastMessage(message: String)

    fun completeLoading()
}