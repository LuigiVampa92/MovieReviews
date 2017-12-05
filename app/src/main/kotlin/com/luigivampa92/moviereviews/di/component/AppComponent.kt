package com.luigivampa92.moviereviews.di.component

import com.luigivampa92.moviereviews.di.module.AppModule
import com.luigivampa92.moviereviews.di.module.RestModule
import com.luigivampa92.moviereviews.domain.MainPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, RestModule::class))
interface AppComponent {
    fun inject(to: MainPresenter)
}