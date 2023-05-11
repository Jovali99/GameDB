package com.ltu.m7019e.v23.themoviedb.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import com.ltu.m7019e.v23.themoviedb.databinding.GenreMovieItemBinding
import com.ltu.m7019e.v23.themoviedb.model.Movie

class GenreMovieListAdapter() : ListAdapter<Movie, GenreMovieListAdapter.ViewHolder>(GenreMovieListDiffCallback()) {

    class ViewHolder(private val binding: GenreMovieItemBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bind(movie: Movie) {
            binding.movie = movie
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = GenreMovieItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }

    override fun getItemCount(): Int {
        val count = super.getItemCount()
        Log.d("rec_test", "getItemCount: count = $count")
        return count
    }



    class GenreMovieListDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
}
