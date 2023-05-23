package com.ltu.m7019e.v23.themoviedb.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ltu.m7019e.v23.themoviedb.model.Game
import java.lang.IllegalArgumentException

class GameDetailViewModelFactory(private val application: Application, private val game: Game): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(GameDetailViewModel::class.java)) {
            return GameDetailViewModel(application, game) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}