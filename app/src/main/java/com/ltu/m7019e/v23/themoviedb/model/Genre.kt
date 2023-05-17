package com.ltu.m7019e.v23.themoviedb.model

import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    var gameList: List<Game>? = null
)
