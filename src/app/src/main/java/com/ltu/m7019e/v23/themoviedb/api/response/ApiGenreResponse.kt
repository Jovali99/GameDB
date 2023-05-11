package com.ltu.m7019e.v23.themoviedb.api.response

import com.google.gson.annotations.SerializedName
import com.ltu.m7019e.v23.themoviedb.model.Genre

data class ApiGenreResponse(
    @SerializedName("genres")
    val genres: List<Genre>
)
