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

class GameListViewModel(application: Application) : AndroidViewModel(application) {
    private val _gameList = MutableLiveData<List<Game>?>()
    val gameList: LiveData<List<Game>?>
        get() {
            return _gameList
        }

    private val _navigateToGameDetail = MutableLiveData<Game?>()
    val navigateToGameDetail: MutableLiveData<Game?>
        get() {
            return _navigateToGameDetail
        }

    init {
        getGamesApiCall { gameList ->
            gameList?.forEach { gameInList ->
                getGamesDetailsApiCall(gameInList.id) { game ->

                    if (game != null) {
                        Log.d("game_list_details", "game details b4: " + gameInList)
                        var desc = game.description?.replace("<p>", "")
                        desc = desc?.replace("</p>", "")
                        desc = desc?.replace("<br>", "")
                        desc = desc?.replace("</br>", "")
                        desc = desc?.replace("<br>", "")
                        desc = desc?.replace("<br />", "")
                        gameInList.description = desc

                        //gameInList.genres = game.genres
                        Log.d("game_list_details", "game details after: " + gameInList)
                    }

                }
            }
            _gameList.postValue(gameList)
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
                Log.d("error_game_list", "game list error : " + error)
            } else if (gameList != null) {
                Log.d("game_list", "add games to list: " + gameList)
                callback(gameList)
            }
        }
    }

    private fun getGamesDetailsApiCall(id: Int?, callback: (Game?) -> Unit) {
        val gameApiClient = GameApiClient()
        gameApiClient.getGameDetails(id.toString()) { game, error ->
            if (error != null ) {
                Log.d("error_game_details", "game_details error : " + error)
            } else if (game != null) {
                Log.d("game_details", "add game_details: " + game)
                callback(game)
            }
        }
    }
}