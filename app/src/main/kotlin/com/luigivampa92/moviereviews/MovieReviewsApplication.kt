package com.luigivampa92.moviereviews

import android.app.Application
import com.luigivampa92.moviereviews.di.component.AppComponent
import com.luigivampa92.moviereviews.di.component.DaggerAppComponent
import com.luigivampa92.moviereviews.di.module.AppModule

class MovieReviewsApplication : Application() {

    companion object {
        @JvmStatic lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        createAppComponent()
    }

    private fun createAppComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }
}