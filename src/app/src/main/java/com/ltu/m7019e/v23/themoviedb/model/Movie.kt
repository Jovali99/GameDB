package com.ltu.m7019e.v23.themoviedb.model

import com.google.gson.annotations.SerializedName

data class Movie(
        var poster_path: String,
        var overview: String,
        var release_date: String,
        @SerializedName("genre_ids")
        var movie_genres: List<Int>,
        var id: Int = 0,
        @SerializedName("original_title")
        var title: String,
        var popularity: Float  = 0f,
        var vote_average: Float = 0f,
        var imdb_link: String
)