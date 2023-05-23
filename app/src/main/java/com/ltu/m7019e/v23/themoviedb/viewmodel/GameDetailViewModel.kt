package com.ltu.m7019e.v23.themoviedb.viewmodel

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.FrameLayout
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ltu.m7019e.v23.themoviedb.R
import com.ltu.m7019e.v23.themoviedb.api.GameApiClient
import com.ltu.m7019e.v23.themoviedb.model.Game

class GameDetailViewModel(application: Application, game: Game) : AndroidViewModel(application) {
    init {
        //getGamesApiCall { gameList ->

            //_gameList.postValue(gameList)
        //}
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
}