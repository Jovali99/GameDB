package com.ltu.m7019e.v23.themoviedb.api

import com.ltu.m7019e.v23.themoviedb.api.response.ApiGenreResponse
import com.ltu.m7019e.v23.themoviedb.api.response.ApiGameResponse
import com.ltu.m7019e.v23.themoviedb.api.response.ApiPopularGamesListResponse
import com.ltu.m7019e.v23.themoviedb.model.Game
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("appdetails{id}")  //Fetch game
    fun getGameDetails(
        @Path("id") id: Int?,
        @Query("key") apiKey: String
    ): Call<ApiGameResponse>

    //@GET("games")
    @GET("{interface}/GetAppList/v2")
    fun getGames(
        @Path("interface") intface: String,
        @Query("key") apiKey: String
    ): Call<ApiPopularGamesListResponse>

    @GET("genres")              //Fetch subjects
    fun getGenres(
        @Path("subject") subject: String,
        @Query("key") apiKey: String
    ): Call<ApiGenreResponse>

}