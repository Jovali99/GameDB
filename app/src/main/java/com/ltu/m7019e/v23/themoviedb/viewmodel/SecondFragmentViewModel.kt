package com.ltu.m7019e.v23.themoviedb.viewmodel

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.FrameLayout
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ltu.m7019e.v23.themoviedb.R
import com.ltu.m7019e.v23.themoviedb.api.GameApiClient
import com.ltu.m7019e.v23.themoviedb.model.Game
import com.ltu.m7019e.v23.themoviedb.model.Genre

class SecondFragmentViewModel (application: Application) : AndroidViewModel(application){
    private val _gameList = MutableLiveData<List<Game>>()
    private val _genreList = MutableLiveData<List<Genre>>()

    val gameList: LiveData<List<Game>>
        get() {
            return _gameList
        }

    private val _navigateToGameDetail = MutableLiveData<Game?>()
    val navigateToGameDetail: MutableLiveData<Game?>
        get() {
            return _navigateToGameDetail
        }

    val genreList: LiveData<List<Genre>>
        get() {
            return _genreList
        }



    init {

        getGamesApiCall { gameList ->
            Log.d("game_-list", "gamelist after api: " + gameList)

            /*gameList.forEach { gameInList ->

                Log.d("game_details", "Before game detail api call: " + gameInList.id)
                /*getGamesDetailsApiCall(gameInList.id) { game ->
                    if (game != null) {
                        game.forEach { gameDetailList ->
                            gameInList.description = gameDetailList.description
                            Log.d(
                                "game_details",
                                "Inside game detail api call: " + gameInList.description
                            )
                        }
                    }
                }*/
            }*/
            //_gameList.postValue(gameList)


//            val genreGame = gameList.filter { it.game_genres.contains(genre.id)}
//            var listOfGenres = mutableListOf<Genre>()
//            listOfGenres.add(genre)
//            _genreList.postValue(listOfGenres) todo genres



        }
    }

    fun onGameListItemClicked(game: Game) {
        Log.d("test", "test2")
        _navigateToGameDetail.value = game

    }

    fun onGameDetailNavigated() {
        _navigateToGameDetail.value = null;
    }

    private fun getGamesApiCall(callback: (List<Game>?) -> Unit) {
        val gameApiClient = GameApiClient()
        gameApiClient.getGames() { gameList, error ->
            if (error != null ) {
                Log.d("error_gamme_list", "game list error : " + error)
            } else if (gameList != null) {
                Log.d("game_list", "add games to list: " + gameList)
                callback(gameList)
            }
        }
    }

//    private fun getGenresApiCall(callback: (List<Genre>) -> Unit) {
//        val gameApiClient = GameApiClient()
//        gameApiClient.getGenres { genreList, error ->
//            if (error != null ) {
//                Log.d("error_game_list", "game list error : " + error)
//            } else if (genreList != null) {
//                Log.d("getGenres", "add genres to list: " + genreList)
//                callback(genreList)
//            }
//        }
//    }

    /*private fun getGamesDetailsApiCall(id: Int?, callback: (List<Game>?) -> Unit) {
        val gameApiClient = GameApiClient()
        gameApiClient.getGameDetails(id) { game, error ->
            if (error != null ) {
                Log.d("error_game_details", "game_details error : " + error)
            } else if (game != null) {
                Log.d("game_details", "add game_details: " + game)
                callback(game)
            }
        }
    }*/

    fun on_imdb_click(context: Context, popUp: AlertDialog, imdb_link: String) {
        popUp.findViewById<FrameLayout>(R.id.imdb_logo_link)?.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(imdb_link))
            context.startActivity(intent)
        }
    }
}