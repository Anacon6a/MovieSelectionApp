package com.example.movieselectionapp.data.remote.api

class RestApi(private val restApiService: RestApiService) {
    suspend fun getMovies(offset: Int) = restApiService.getMovies(offset)
}