package com.example.movieselectionapp.data.repository

import androidx.paging.PagingData
import com.example.movieselectionapp.data.model.MovieModel
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovies(): Flow<PagingData<MovieModel>>
}