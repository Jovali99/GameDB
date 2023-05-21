package com.ltu.m7019e.v23.themoviedb.api

import com.ltu.m7019e.v23.themoviedb.api.response.ApiGameResponse
import com.ltu.m7019e.v23.themoviedb.api.response.ApiPlatformResponse
import com.ltu.m7019e.v23.themoviedb.api.response.ApiPopularGamesListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("games/{id}")  //Fetch game
    fun getGameDetails(
        @Path("id") id: String?,
        @Query("key") apiKey: String
    ): Call<ApiGameResponse>


    //@GET("{interface}/GetAppList/v2")
    @GET("games")
    fun getGames(
        @Query("sort") sort: String,
        //@Path("interface") intface: String,
        @Query("key") apiKey: String
    ): Call<ApiPopularGamesListResponse>

    @GET("genres")              //Fetch subjects
    fun getGenres(
        @Path("subject") subject: String,
        @Query("key") apiKey: String
    ): Call<ApiPlatformResponse>

}