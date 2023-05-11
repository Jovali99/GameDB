package com.ltu.m7019e.v23.themoviedb.api

import android.util.Log
import com.ltu.m7019e.v23.themoviedb.api.response.ApiGenreResponse
import com.ltu.m7019e.v23.themoviedb.api.response.ApiMovieResponse
import com.ltu.m7019e.v23.themoviedb.api.response.ApiPopularMoviesListResponse
import com.ltu.m7019e.v23.themoviedb.database.Genres
import com.ltu.m7019e.v23.themoviedb.model.Genre
import com.ltu.m7019e.v23.themoviedb.model.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory

class MovieApiClient {

    private val API_KEY = "4fb01808f41695becd422df6b0053141"
    private val PAGES = 1
    private val LANGUAGE = "en-US"

    private val apiService: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiService::class.java)
    }

    fun getGenres(language: String = LANGUAGE, callback: (List<Genre>?, Throwable?) -> Unit) {
        apiService.getGenres(language = LANGUAGE, apiKey = API_KEY).enqueue(object : Callback<ApiGenreResponse> {
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
    }

    fun getPopularMovies(page: Int = PAGES, callback: (List<Movie>?, Throwable?) -> Unit) {
        apiService.getPopularMovies(pages = page, apiKey = API_KEY).enqueue(object : Callback<ApiPopularMoviesListResponse> {
            override fun onResponse(call: Call<ApiPopularMoviesListResponse>, response: Response<ApiPopularMoviesListResponse>) {
                if (response.isSuccessful) {
                    val apiPopMovieResponse = response.body()
                    Log.d("pop_movie_list", "api response: "+ apiPopMovieResponse)
                    val movieList = apiPopMovieResponse?.popularMovies?.map { movieObj ->
                        movieObj?.toMovie()
                    }
                    callback(movieList as List<Movie>?,null)

                } else {
                    callback(null, Throwable(response.message()))
                }
            }
            override fun onFailure(call: Call<ApiPopularMoviesListResponse>, t: Throwable) {
                callback(null, t)
            }
        })
    }

    fun getMovieDetails(movieID: Int, callback: (String?, Throwable?) -> Unit) {
        apiService.getMovieDetails(movieID, apiKey = API_KEY).enqueue(object : Callback<ApiMovieResponse> {
            override fun onResponse(call: Call<ApiMovieResponse>, response: Response<ApiMovieResponse>) {
                if (response.isSuccessful) {
                    val apiMovieDetailsResponse = response.body()
                    Log.d("movie_link", "api response: "+ apiMovieDetailsResponse)
                    val movie_link = apiMovieDetailsResponse?.imdb_link
                    Log.d("movie_link", "api response: "+ movie_link)
                    callback(movie_link,null)
                } else {
                    callback(null, Throwable(response.message()))
                }
            }
            override fun onFailure(call: Call<ApiMovieResponse>, t: Throwable) {
                callback(null, t)
            }
        })
    }

    private fun Genre.toGenre(): Genre? {
        return if (this.id != null) {
            Genre(
                id = this.id ?: 0,
                name = this.name ?: "",
                movieList = null
            )
        } else {
            null
        }
    }

    private fun Movie.toMovie(): Movie? {
        return if (this.id != null) {
            Movie(
                poster_path = this.poster_path ?: "",
                overview = this.overview ?: "",
                release_date = this.release_date ?: "",
                movie_genres = this.movie_genres ?: listOf(),
                id = this.id ?: 0,
                title = this.title ?: "",
                popularity = this.popularity ?: 0f,
                vote_average = this.vote_average ?: 0f,
                imdb_link = this.imdb_link ?: ""
            )
        } else {
            null
        }
    }
}






