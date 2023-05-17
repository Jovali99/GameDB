package com.ltu.m7019e.v23.themoviedb.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ltu.m7019e.v23.themoviedb.model.Genre
import com.ltu.m7019e.v23.themoviedb.databinding.GenreItemListBinding
import androidx.recyclerview.widget.ListAdapter
import com.ltu.m7019e.v23.themoviedb.R

class GenreListAdapter() : ListAdapter<Genre, GenreListAdapter.ViewHolder>(GenreListDiffCallback()) {

    class ViewHolder(private val binding: GenreItemListBinding) : RecyclerView.ViewHolder(binding.root) {

        private val genreGameListRV: RecyclerView
        val genreGameListAdapter: GenreGameListAdapter
        init {
            val context = itemView.context
            genreGameListRV = itemView.findViewById(R.id.genre_game_list_rv)
            genreGameListRV?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            genreGameListAdapter = GenreGameListAdapter()
            genreGameListRV.adapter = genreGameListAdapter
        }

        fun bind(genre: Genre) {
            binding.genreName.text = genre.description
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = GenreItemListBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val genre = getItem(position)
        if (genre != null) {
            holder.genreGameListAdapter.submitList(genre.gameList)
            holder.bind(genre)
        }
    }

    override fun getItemCount(): Int {
        val count = super.getItemCount()
        Log.d("rec_test", "getItemCount: count = $count")
        return count
    }

    class GenreListDiffCallback : DiffUtil.ItemCallback<Genre>() {
        override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean {
            return oldItem == newItem
        }

    }
}
