package com.luigivampa92.moviereviews.domain

import android.content.res.Resources
import com.arellomobile.mvp.InjectViewState
import com.luigivampa92.moviereviews.R
import com.luigivampa92.moviereviews.data.repository.ReviewsRepository
import com.luigivampa92.moviereviews.mvp.MainView
import com.luigivampa92.moviereviews.mvp.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@InjectViewState
class MainPresenter : BasePresenter<MainView>() {

    @Inject lateinit var reviewRepository: ReviewsRepository
    @Inject lateinit var resources: Resources
    private var reviews = mutableListOf<Review>()
    private var lastQuery = ""

    init {
        getAppComponent().inject(this)
    }

    fun getReviews(query: String, forceClear: Boolean) {
        if (forceClear)
            reviews.clear()
        handleQuery(query)

        reviewRepository.getReviews(query, reviews.size)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ items -> run {
                    this.reviews.addAll(items)
                    viewState.displayReviews(reviews)
                    viewState.completeLoading()
                }},
                        { e -> run {
                    viewState.toastMessage(e.message ?: resources.getString(R.string.text_error))
                    viewState.completeLoading()
                }})
    }

    private fun handleQuery(query: String) {
        if (query != lastQuery)
            reviews.clear()
        lastQuery = query
    }
}