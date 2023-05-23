package com.ltu.m7019e.v23.themoviedb

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ltu.m7019e.v23.themoviedb.adapter.GameListAdapter
import com.ltu.m7019e.v23.themoviedb.adapter.GameListClickListener
import com.ltu.m7019e.v23.themoviedb.databinding.FragmentGameListBinding
import com.ltu.m7019e.v23.themoviedb.model.Platform
import com.ltu.m7019e.v23.themoviedb.viewmodel.GameListViewModel
import com.ltu.m7019e.v23.themoviedb.viewmodel.GameListViewModelFactory

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class GameListFragment : Fragment() {

    private lateinit var viewModel: GameListViewModel
    private lateinit var viewModelFactory: GameListViewModelFactory

    private var _binding: FragmentGameListBinding? = null;
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGameListBinding.inflate(inflater)

        val application = requireNotNull(this.activity).application

        viewModelFactory = GameListViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(GameListViewModel::class.java)

        val gameListAdapter = GameListAdapter(
            GameListClickListener { game ->
                viewModel.onGameListItemClicked(game)
            }
        )

        binding.gameListRv.adapter = gameListAdapter

        viewModel.gameList.observe(
            viewLifecycleOwner
        ) { gameList ->
            gameList?.let {
                gameListAdapter.submitList(gameList)
            }
        }


        viewModel.navigateToGameDetail.observe(viewLifecycleOwner) { game ->
            game?.let{
                this.findNavController().navigate(
                    GameListFragmentDirections.actionGameListFragmentToGameDetailsFragment(game)

                )
                viewModel.onGameDetailNavigated()
                /*
                val builder = AlertDialog.Builder(requireContext())
                val popupView = LayoutInflater.from(requireContext()).inflate(R.layout.game_info_popup, null)
                builder.setView(popupView)
                val dialog = builder.create()
                dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) //needed for background

                dialog.show()

                //viewModel.on_imdb_click(requireContext(), dialog, movie.imdb_link) todo correct click
                viewModel.onGameDetailNavigated()*/
            }
        }

        return binding.root
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}