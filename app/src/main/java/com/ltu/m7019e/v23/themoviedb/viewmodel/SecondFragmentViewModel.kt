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
import com.ltu.m7019e.v23.themoviedb.database.Platforms
import com.ltu.m7019e.v23.themoviedb.model.Game
import com.ltu.m7019e.v23.themoviedb.model.Platform

class SecondFragmentViewModel (application: Application) : AndroidViewModel(application){
    private val _gameList = MutableLiveData<List<Game>>()
    private val _platformList = MutableLiveData<List<Platform>>()

    val platformList: LiveData<List<Game>>
        get() {
            return _gameList
        }

    private val _navigateToGameDetail = MutableLiveData<Game?>()
    val navigateToGameDetail: MutableLiveData<Game?>
        get() {
            return _navigateToGameDetail
        }

    val genreList: LiveData<List<Platform>>
        get() {
            return _platformList
        }



    init {
        var listOfPlatforms = Platforms()
        
        getGamesApiCall { gameList ->

            Log.d("game-list", "gamelist after api: " + gameList?.elementAt(2)?.platforms)
            gameList?.forEach { gameInList ->
                //Log.d("platform--", "game platformsss: " + gameInList.platforms)
                gameInList.platforms?.forEach { gamesPlatform ->
                    //Log.d("platform--", "game platforms: " + gamesPlatform)
                    listOfPlatforms.list.forEach { platform ->
                        Log.d("platform--", "platform: " + platform?.platform?.name)
                        Log.d("platform--", "game platform: " + gamesPlatform)
                        if (platform.platform?.name.equals(gamesPlatform.platform?.name)) {
                            Log.d("platform--", "add game to platform: " + platform.platform?.name)
                            Log.d("platform--", "add game to platform: " + gamesPlatform.platform?.name)
                            Log.d("platform--", "game to add: " + gameInList)
                            platform.platform?.gameList?.add(gameInList)
                        }
                    }
                    
                }
            }
            Log.d("platform", "after platform games are added: " + listOfPlatforms.list?.elementAt(0)?.platform?.gameList)
            _platformList.postValue(listOfPlatforms.list)
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

}