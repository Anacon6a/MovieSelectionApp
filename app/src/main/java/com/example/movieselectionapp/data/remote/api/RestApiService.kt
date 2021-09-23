package com.example.movieselectionapp.data.remote.api

import com.example.movieselectionapp.data.remote.dto.AnswerRemote
import com.example.movieselectionapp.data.remote.dto.MovieRemote
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApiService {
    @GET("/svc/movies/v2/reviews/all.json?api-key=mb1AsgFrdVINaKeTiBAfcPeJz46cwJTJ")
    suspend fun getMovies(@Query("offset") offset:Int): Response<AnswerRemote<List<MovieRemote>>>
}