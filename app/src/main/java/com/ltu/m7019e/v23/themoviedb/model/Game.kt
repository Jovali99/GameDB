package com.ltu.m7019e.v23.themoviedb.model

import com.google.gson.annotations.SerializedName

data class Game(
    @SerializedName("appid")
    val appid: Int? = null,
    @SerializedName("name")
    val name: String? = null
    /*@SerializedName("short_description")
    var short_description: String? = null,
    @SerializedName("header_image")
    val header_image: String? = null,*/

)
