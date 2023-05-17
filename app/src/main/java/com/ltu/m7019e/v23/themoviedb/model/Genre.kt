package com.ltu.m7019e.v23.themoviedb.model

import com.google.gson.annotations.SerializedName

data class Genre(
    val id: String,
    val description: String,
    var gameList: List<Game>? = null
)
