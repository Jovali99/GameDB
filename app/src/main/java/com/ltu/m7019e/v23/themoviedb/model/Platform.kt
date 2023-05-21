package com.ltu.m7019e.v23.themoviedb.model

data class Platform(
    val platform: SpecificPlatform? = null,

)

data class SpecificPlatform(
    val name: String? = null,
    val slug: String? = null,
    var gameList: MutableList<Game> = mutableListOf<Game>()
)
