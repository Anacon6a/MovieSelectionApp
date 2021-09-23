package com.example.movieselectionapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MovieRemote(
    @SerializedName("display_title")
    val name: String,
    @SerializedName("summary_short")
    val description: String,
    val multimedia: MultimediaRemote
)
