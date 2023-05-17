package com.ltu.m7019e.v23.themoviedb.api.response

import com.google.gson.annotations.SerializedName
import com.ltu.m7019e.v23.themoviedb.model.Game

data class ApiGameResponse(
    val appid: ApiGame
)

data class ApiGame(
    val success: Boolean,
    val data: Game?
)
