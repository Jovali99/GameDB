/*
package com.ltu.m7019e.v23.themoviedb.database

import com.ltu.m7019e.v23.themoviedb.model.Movie

class Movies {
    var list = mutableListOf<Movie>()

    init {
        */
/*
        getMoviesApiCall { movieList ->
            Log.d("movie_-list", "movie list after api: " + movieList)
            list = movieList as MutableList<Movie>
        }

        *//*

    }
    */
/*
    private fun getMoviesApiCall(callback: (List<Movie>) -> Unit) {
        val bookApiClient = BookApiClient()
        bookApiClient.getPopularMovies(page = 1) { movieList, error ->
            if (error != null ) {
                Log.d("error_movie_list", "movie list error : " + error)
            } else if (movieList != null) {
                Log.d("movie_list", "add movies to list: " + movieList)
                callback(movieList)
            }
        }
    }
    *//*

}*/
