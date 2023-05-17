package com.ltu.m7019e.v23.themoviedb.model

import com.google.gson.annotations.SerializedName

data class Game(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("short_description")
    var short_description: String? = null,
    @SerializedName("background_image")
    var background_image: String? = null,
    @SerializedName("genres")
    var genres: List<Genre>? = null,

)
