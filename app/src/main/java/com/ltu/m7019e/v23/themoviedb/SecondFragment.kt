package com.ltu.m7019e.v23.themoviedb

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ltu.m7019e.v23.themoviedb.adapter.GenreListAdapter
import com.ltu.m7019e.v23.themoviedb.databinding.FragmentSecondBinding
import com.ltu.m7019e.v23.themoviedb.viewmodel.SecondFragmentViewModel
import com.ltu.m7019e.v23.themoviedb.viewmodel.SecondFragmentViewModelFactory


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private lateinit var viewModel: SecondFragmentViewModel
    private lateinit var viewModelFactory: SecondFragmentViewModelFactory

    private var _binding: FragmentSecondBinding? = null;
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater)

        val application = requireNotNull(this.activity).application

        viewModelFactory = SecondFragmentViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SecondFragmentViewModel::class.java)

        // Set up the RecyclerView
        val adapter = GenreListAdapter()
        binding.genreListRv.adapter = adapter

        viewModel.genreList.observe(
            viewLifecycleOwner
        ) { genreList ->
            genreList?.let {
                adapter.submitList(genreList)
            }
        }

        /*
        viewModel.navigateToMovieDetail.observe(viewLifecycleOwner) { movie ->
            movie?.let{
                // inflate the popup dialog layout
                val builder = AlertDialog.Builder(requireContext())
                val popupView = LayoutInflater.from(requireContext()).inflate(R.layout.movie_info_popup, null)
                builder.setView(popupView)
                val dialog = builder.create()
                dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) //needed for background

                dialog.show()

                viewModel.on_imdb_click(requireContext(), dialog, movie.imdb_link)


                viewModel.onMovieDetailNavigated()
            }
        }*/

        // Inflate the layout for this fragment
        Log.d("rec_test", "onCreateView: "+ binding)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*
        val genreLayoutContainer = view.findViewById<LinearLayout>(R.id.genre_layout)
        val movies = Movies()

        getGenresApiCall { genreList ->
            var genres = Genres()
            genres.list = genres.addGenres(genreList)

            Log.d("genre_list", "filled genre list: " + genres.list)

            genres.list.forEach { genre ->
                val genreItem = LayoutInflater.from(requireContext()).inflate(R.layout.genre_item_list, genreLayoutContainer, false)

                val genreMovies = movies.list.filter { it.movie_genres.contains(genre.second)}   // Filter movies
                if (genreMovies.isEmpty()) {
                    // If no movies of the genre exist we remove the genre view
                    genreLayoutContainer.removeView(genreItem)
                } else {
                    genreItem.findViewById<TextView>(R.id.genre_name)?.text = genre.first
                    genreLayoutContainer.addView(genreItem)
                }

                val movieListLayoutContainer = genreItem.findViewById<LinearLayout>(R.id.list_of_movies)
                // Adds all the movies with the corresponding genre to the list_of_movies view
                genreMovies.forEach { movie ->
                    val movieItem = DataBindingUtil.inflate<GenreMovieItemBinding>(LayoutInflater.from(requireContext()), R.layout.genre_movie_item, movieListLayoutContainer, false)
                    movieItem.movie = movie
                    var imdb_link = movie.imdb_link
                    // Creates a popup dialogue which will display information as well as a link to imbd
                    createMovieInfoPopup(movieItem.root, imdb_link)

                    // On click action
                    movieListLayoutContainer.addView(movieItem.root)
                }
            }
        }*/

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

    }

}