package com.example.movieselectionapp

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.example.movieselectionapp.data.remote.api.RestApi
import com.example.movieselectionapp.data.remote.api.ServiceBuilder
import com.example.movieselectionapp.data.repository.MovieRepository
import com.example.movieselectionapp.data.repository.MovieRepositoryImpl
import com.example.movieselectionapp.extensions.bindViewModel
import com.example.movieselectionapp.ui.viewmodel.MovieFeedViewModel
import com.example.movieselectionapp.ui.viewmodel.ViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.direct
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import timber.log.Timber

class MovieSelectionApp : Application(), KodeinAware {

    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@MovieSelectionApp))

        bind<ViewModelProvider.Factory>() with singleton { ViewModelFactory(kodein.direct) }

        bind<MovieRepository>() with provider { MovieRepositoryImpl(instance()) }

        bindViewModel<MovieFeedViewModel>() with provider {
            MovieFeedViewModel(instance())
        }

        bind() from singleton { RestApi(ServiceBuilder.restApiService) }
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}