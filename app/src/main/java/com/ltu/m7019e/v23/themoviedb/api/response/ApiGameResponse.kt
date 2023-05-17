package com.ltu.m7019e.v23.themoviedb.api.response

import com.google.gson.annotations.SerializedName
import com.ltu.m7019e.v23.themoviedb.model.Game

data class ApiGameResponse(
    val id: String,
    //val appid: Map<String, ApiData>

)

data class ApiData(
    val success: Boolean,
    val data: Game?
)
