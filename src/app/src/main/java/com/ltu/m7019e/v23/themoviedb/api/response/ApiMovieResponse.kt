package com.ltu.m7019e.v23.themoviedb.api.response

import com.google.gson.annotations.SerializedName
import com.ltu.m7019e.v23.themoviedb.model.Movie

data class ApiMovieResponse(
    @SerializedName("imdb_id")
    val imdb_link: String? = null,
)
