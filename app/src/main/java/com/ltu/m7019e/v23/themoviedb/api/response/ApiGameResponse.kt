package com.ltu.m7019e.v23.themoviedb.api.response

import com.google.gson.annotations.SerializedName
import com.ltu.m7019e.v23.themoviedb.model.Game

data class ApiGameResponse(
    @SerializedName("platforms")
    val gameDetails: List<List<Game>?>? = null  //todo fel
)




