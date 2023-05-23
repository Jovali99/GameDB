package com.ltu.m7019e.v23.themoviedb.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Platform(
    val platform: SpecificPlatform? = null,

) : Parcelable
@Parcelize
data class SpecificPlatform(
    val name: String? = null,
    val slug: String? = null,
    var gameList: MutableList<Game> = mutableListOf<Game>()
) : Parcelable
