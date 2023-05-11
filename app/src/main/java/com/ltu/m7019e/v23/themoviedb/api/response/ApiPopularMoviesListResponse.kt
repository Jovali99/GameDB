package com.ltu.m7019e.v23.themoviedb.api.response

import com.google.gson.annotations.SerializedName
import com.ltu.m7019e.v23.themoviedb.model.Genre
import com.ltu.m7019e.v23.themoviedb.model.Movie

data class ApiPopularMoviesListResponse(
    @SerializedName("results")
    val popularMovies: List<Movie>? = null
)
