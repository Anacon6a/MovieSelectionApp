package com.example.movieselectionapp.data.mapper

import com.example.movieselectionapp.data.model.MovieModel
import com.example.movieselectionapp.data.remote.dto.MovieRemote

fun mapMovie(movies: List<MovieRemote>) = movies.map {
    MovieModel(it.name, it.description, it.multimedia.image)
}