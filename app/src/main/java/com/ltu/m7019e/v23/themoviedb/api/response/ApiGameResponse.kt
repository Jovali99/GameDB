package com.ltu.m7019e.v23.themoviedb.api.response

import com.google.gson.annotations.SerializedName
import com.ltu.m7019e.v23.themoviedb.model.Game

data class ApiGameResponse(
    val id: Int? = null,
    val description: String? = null,
    val rating: Double?  = null
)

data class ApiData(
    val data: Game?
)
