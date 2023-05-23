package com.ltu.m7019e.v23.themoviedb.model
import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Game(
    val id: Int? = null,
    val slug: String? = null,
    val name: String? = null,
    val name_original: String? = null,
    var description: String? = null,
    val metacritic: Int? = null,
    val metacritic_platforms: List<MetaCriticPlatform>? = null,
    val released: String? = null,
    val tba: Boolean? = null,
    val updated: String? = null,
    val background_image: String? = null,
    val background_image_additional: String? = null,
    val website: String? = null,
    val rating: Double? = null,
    val rating_top: Int? = null,
    //val ratings: Map<String, Rating>? = null,
    val ratings: List<Rating>? = null,
    val reactions: Map<String, Int>? = null,
    val added: Int? = null,
    val added_by_status: Map<String, Int>? = null,
    val playtime: Int? = null,
    val screenshots_count: Int? = null,
    val movies_count: Int? = null,
    val creators_count: Int? = null,
    val achievements_count: Int? = null,
    val parent_achievements_count: String? = null,
    val reddit_url: String? = null,
    val reddit_name: String? = null,
    val reddit_description: String? = null,
    val reddit_logo: String? = null,
    val reddit_count: Int? = null,
    val twitch_count: String? = null,
    val youtube_count: String? = null,
    val reviews_text_count: String? = null,
    val ratings_count: Int? = null,
    val suggestions_count: Int? = null,
    val alternative_names: List<String>? = null,
    val metacritic_url: String? = null,
    val parents_count: Int? = null,
    val additions_count: Int? = null,
    val game_series_count: Int? = null,
    val esrb_rating: EsrbRating? = null,
    val platforms: List<Platform>? = null,
    val gameTrailer: String? = null
) : Parcelable

@Parcelize
data class MetaCriticPlatform(
    val metascore: Int? = null,
    val url: String? = null,
    val platform: Platform? = null
): Parcelable
@Parcelize
data class Rating(
    val id: Int? = null,
    val title: String? = null,
    val count: Int? = null,
    val percent: Double? = null
): Parcelable
@Parcelize
data class EsrbRating(
    val id: Int? = null,
    val slug: String? = null,
    val name: String? = null
): Parcelable


