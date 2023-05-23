package com.ltu.m7019e.v23.themoviedb

import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.MediaController
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.ltu.m7019e.v23.themoviedb.databinding.FragmentGameDetailsBinding
import com.ltu.m7019e.v23.themoviedb.databinding.FragmentGameListBinding
import com.ltu.m7019e.v23.themoviedb.viewmodel.GameListViewModel
import com.ltu.m7019e.v23.themoviedb.viewmodel.GameListViewModelFactory
import com.ltu.m7019e.v23.themoviedb.model.Game
import com.ltu.m7019e.v23.themoviedb.viewmodel.GameDetailViewModel
import com.ltu.m7019e.v23.themoviedb.viewmodel.GameDetailViewModelFactory

class GameDetailsFragment : Fragment() {

    private lateinit var viewModel: GameDetailViewModel
    private lateinit var viewModelFactory: GameDetailViewModelFactory

    private var _binding: FragmentGameDetailsBinding? = null;
    private val binding get() = _binding!!

    private lateinit var game: Game

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGameDetailsBinding.inflate(inflater)
        binding.lifecycleOwner = this
        game = GameDetailsFragmentArgs.fromBundle(requireArguments()).game

        val application = requireNotNull(this.activity).application

        viewModelFactory = GameDetailViewModelFactory(application, game)
        viewModel = ViewModelProvider(this, viewModelFactory).get(GameDetailViewModel::class.java)

        binding.game = game
        binding.viewModel = viewModel


        //playVideoFromUrl("https://www.youtube.com/watch?v=7gL9mopbm3Y")
        playVideoFromUrl("https:/steamcdn-a.akamaihd.net/steam/apps/256683844/movie480.mp4")

        return binding.root
        //return inflater.inflate(R.layout.fragment_game_details, container, false)
    }

    private fun playVideoFromUrl(videoUrl: String) {
        val videoView = binding.videoView

        videoView.webViewClient = WebViewClient()
        videoView.settings.javaScriptEnabled = true

        // Load a web page
        videoView.loadUrl(videoUrl)

    }
}