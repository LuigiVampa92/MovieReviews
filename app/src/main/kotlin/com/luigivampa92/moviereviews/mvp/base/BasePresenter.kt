package com.luigivampa92.moviereviews.mvp.base

import com.arellomobile.mvp.MvpPresenter
import com.luigivampa92.moviereviews.MovieReviewsApplication
import com.luigivampa92.moviereviews.di.component.AppComponent

abstract class BasePresenter<V : BaseView> : MvpPresenter<V>() {

    protected fun getAppComponent(): AppComponent {
        return MovieReviewsApplication.appComponent
    }
}