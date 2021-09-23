package com.example.movieselectionapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieselectionapp.data.repository.MovieRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import timber.log.Timber

class MovieFeedViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    fun getMovies() = flow {
        movieRepository.getMovies().collect { emit(it) }
    }
}