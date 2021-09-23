package com.example.movieselectionapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movieselectionapp.data.mapper.mapMovie
import com.example.movieselectionapp.data.model.MovieModel
import com.example.movieselectionapp.data.pagingsource.MoviePagingSource
import com.example.movieselectionapp.data.remote.api.RestApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher
import timber.log.Timber


class MovieRepositoryImpl(
    private val restApi: RestApi
) : MovieRepository {

    override suspend fun getMovies() =
        Pager(
            PagingConfig(
                prefetchDistance = 10,
                pageSize = 20,
            ),
            pagingSourceFactory = {
                MoviePagingSource(restApi)
            }
        ).flow.flowOn(Dispatchers.IO)

}