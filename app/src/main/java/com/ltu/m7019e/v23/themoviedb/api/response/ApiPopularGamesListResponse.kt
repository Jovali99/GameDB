package com.ltu.m7019e.v23.themoviedb.api.response

import com.google.gson.annotations.SerializedName
import com.ltu.m7019e.v23.themoviedb.model.Game

data class ApiPopularGamesListResponse(
    val applist: AppList
)

data class AppList(
    val apps: List<Game>? = null
)
