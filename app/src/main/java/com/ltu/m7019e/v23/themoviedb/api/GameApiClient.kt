package com.ltu.m7019e.v23.themoviedb.api

import android.util.Log
import com.google.gson.GsonBuilder
import com.ltu.m7019e.v23.themoviedb.api.response.ApiGameResponse
import com.ltu.m7019e.v23.themoviedb.api.response.ApiGameTrailerResponse
import com.ltu.m7019e.v23.themoviedb.api.response.ApiPopularGamesListResponse
import com.ltu.m7019e.v23.themoviedb.model.Game
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory

class GameApiClient {
    private val API_KEY = "3acc37d9ef4147a891d5f380bca68996" // rawg
    //private val API_KEY = "625DCAAF27AA89204BCB57F0681D28A5" // steam
    private val SORT = "popular"

    private val apiService1: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.rawg.io/api/")
            //.baseUrl("https://api.steampowered.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiService::class.java)
    }

    /*
    fun getGenres(subject: String, callback: (List<Genre>?, Throwable?) -> Unit) {
        apiService.getGenres(subject, apiKey = API_KEY).enqueue(object : Callback<ApiGenreResponse> {
            override fun onResponse(call: Call<ApiGenreResponse>, response: Response<ApiGenreResponse>) {
                if (response.isSuccessful) {
                    val apiGenreResponse = response.body()
                    Log.d("genre_list", "api response: "+ apiGenreResponse)
                    val genreList = apiGenreResponse?.genres?.map { genreObj ->
                        genreObj?.toGenre()
                    }
                    callback(genreList as List<Genre>?,null)

                } else {
                    callback(null, Throwable(response.message()))
                }
            }
            override fun onFailure(call: Call<ApiGenreResponse>, t: Throwable) {
                callback(null, t)
            }
        })
    }*/

    fun getGameTrailer(id: String?, callback: (String?, Throwable?) -> Unit) {
        apiService1.getGameTrailer(id, apiKey = API_KEY).enqueue(object : Callback<ApiGameTrailerResponse> {
            override fun onResponse(call: Call<ApiGameTrailerResponse>, response: Response<ApiGameTrailerResponse>) {
                if (response.isSuccessful) {
                    Log.d("gameTrailer", "api response success: "+ response)
                    val apiGameTrailersResponse = response.body()
                    Log.d("gameTrailer", "api response body: "+ apiGameTrailersResponse)
                    //val trailerUrl = apiGameTrailersResponse?.preview
                    //callback(trailerUrl,null)
                } else {
                    Log.d("gameTrailer", "api response fail: "+ response)
                    callback(null, Throwable(response.message()))
                }
            }
            override fun onFailure(call: Call<ApiGameTrailerResponse>, t: Throwable) {
                Log.d("gameTrailer", "api response fail2: "+ t)
                callback(null, t)
            }
        })
    }

    fun getGames(sort: String = SORT, apiKey: String =API_KEY, callback: (List<Game>?, Throwable?) -> Unit) {
        apiService1.getGames(sort, apiKey).enqueue(object : Callback<ApiPopularGamesListResponse> {
            override fun onResponse(call: Call<ApiPopularGamesListResponse>, response: Response<ApiPopularGamesListResponse>) {
                if (response.isSuccessful) {
                    Log.d("gameList_good", "api response: "+ response)
                    val apiGamesResponse = response.body()
                    Log.d("gameList_good", "api body: "+ apiGamesResponse)
                    val gameList = apiGamesResponse?.results?.map { GameObj ->
                        GameObj?.toGame()
                    }
                    callback(gameList as List<Game>?,null)

                } else {
                    Log.d("gameList_no_response", "api response: "+ response.message())
                    callback(null, Throwable(response.message()))
                }
            }
            override fun onFailure(call: Call<ApiPopularGamesListResponse>, t: Throwable) {
                Log.d("gameList_list_failure", "api response: " + t)
                callback(null, t)
            }
        })
    }

    fun getGameDetails(id: String?, callback: (Game?, Throwable?) -> Unit) {
        apiService1.getGameDetails(id, apiKey = API_KEY).enqueue(object : Callback<ApiGameResponse> {
            override fun onResponse(call: Call<ApiGameResponse>, response: Response<ApiGameResponse>) {
                if (response.isSuccessful) {
                    Log.d("gameDetails", "api response success: "+ response)
                    val apiGameDetailsResponse = response.body()
                    Log.d("gameDetails", "api response body: "+ apiGameDetailsResponse)
                    val game = apiGameDetailsResponse?.toGame()
                    callback(game,null)
                } else {
                    Log.d("gameDetails", "api response fail: "+ response)
                    callback(null, Throwable(response.message()))
                }
            }
            override fun onFailure(call: Call<ApiGameResponse>, t: Throwable) {
                Log.d("gameDetails", "api response fail2: "+ t)
                callback(null, t)
            }
        })
    }

    private fun Game.toGame(): Game? {
        return if (this.id != null) {
            Game(
                id = this.id ?: 0,
                name = this.name ?: "",
                description = this.description ?: "",
                background_image = this.background_image ?: "",
                rating=this.rating ?: null,
                platforms = this.platforms ?: null
            )
        } else {
            null
        }
    }

    private fun ApiGameResponse.toGame(): Game? {
        return if (this.id != null) {
            Game(
                id = this.id ?: 0,
                description = this.description ?: "",
                rating=this.rating ?: null
            )
        } else {
            null
        }
    }

    /*
    private fun Genre.toGenre(): Genre? {
        return if (this.id != null) {
            Genre(
                id = this.id,
                name = this.name ?: "",
                movieList = null
            )
        } else {
            null
        }
    }*/


}






