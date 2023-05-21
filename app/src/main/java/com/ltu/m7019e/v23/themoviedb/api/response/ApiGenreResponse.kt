package com.ltu.m7019e.v23.themoviedb.api.response

import com.google.gson.annotations.SerializedName
import com.ltu.m7019e.v23.themoviedb.model.Platform

data class ApiPlatformResponse(
    @SerializedName("genres")
    val platform: List<Platform>
)
