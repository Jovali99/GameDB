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
        getGameTrailer(game.id) { gameTrailerString ->
            Log.d("gameTrailer", "gameTrailerString : " + gameTrailerString)
            //_gameList.postValue(gameList)
        }
    }

    private fun getGameTrailer(id: Int?, callback: (String?) -> Unit) {
        val gameApiClient = GameApiClient()
        gameApiClient.getGameTrailer(id.toString()) { trailerString, error ->
            if (error != null ) {
                Log.d("gameTrailer", "gameTrailer error : " + error)
            } else if (trailerString != null) {
                Log.d("gameTrailer", "gameTrailer to string: " + trailerString)
                callback(trailerString)
            }
        }
    }
}