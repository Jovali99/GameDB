package com.ltu.m7019e.v23.themoviedb.viewmodel

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.FrameLayout
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ltu.m7019e.v23.themoviedb.R
import com.ltu.m7019e.v23.themoviedb.api.MovieApiClient
import com.ltu.m7019e.v23.themoviedb.database.Movies
import com.ltu.m7019e.v23.themoviedb.model.Movie

class MovieListViewModel(application: Application) : AndroidViewModel(application) {
    val IMDB_BASE_LINK = "https://www.imdb.com/title/"
    private val _movieList = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>>
        get() {
            return _movieList
        }

    private val _navigateToMovieDetail = MutableLiveData<Movie?>()
    val navigateToMovieDetail: MutableLiveData<Movie?>
        get() {
            return _navigateToMovieDetail
        }

    init {
        getMoviesApiCall { movieList ->
            Log.d("movie_-list", "movie list after api: " + movieList)

            movieList.forEach { movieInList ->
                Log.d("movie_details", "Before movie detail api call: " + movieInList.id)
                getMoviesDetailsApiCall(movieInList.id) { link ->

                    movieInList.imdb_link = IMDB_BASE_LINK + link
                    Log.d("movie_details", "Inside movie detail api call: " + movieInList.imdb_link)
                }
            }

            _movieList.postValue(movieList)
        }
    }

    fun onMovieListItemClicked(movie: Movie) {
        Log.d("test", "test2")
        _navigateToMovieDetail.value = movie

    }

    fun onMovieDetailNavigated() {
        _navigateToMovieDetail.value = null;
    }


    private fun getMoviesApiCall(callback: (List<Movie>) -> Unit) {
        val movieApiClient = MovieApiClient()
        movieApiClient.getPopularMovies(page = 3) { movieList, error ->
            if (error != null ) {
                Log.d("error_movie_list", "movie list error : " + error)
            } else if (movieList != null) {
                Log.d("movie_list", "add movies to list: " + movieList)
                callback(movieList)
            }
        }
    }

    private fun getMoviesDetailsApiCall(id: Int, callback: (String) -> Unit) {
        val movieApiClient = MovieApiClient()
        movieApiClient.getMovieDetails(id) { link, error ->
            if (error != null ) {
                Log.d("error_movie_details", "movie_details error : " + error)
            } else if (link != null) {
                Log.d("movie_details", "add movie_details: " + link)
                callback(link)
            }
        }
    }

    fun on_imdb_click(context: Context, popUp: AlertDialog, imdb_link: String) {
        popUp.findViewById<FrameLayout>(R.id.imdb_logo_link)?.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(imdb_link))
            context.startActivity(intent)
        }
    }
}