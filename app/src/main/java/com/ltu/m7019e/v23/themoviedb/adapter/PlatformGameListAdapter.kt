package com.ltu.m7019e.v23.themoviedb.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import com.ltu.m7019e.v23.themoviedb.databinding.PlatformGameItemBinding
import com.ltu.m7019e.v23.themoviedb.model.Game

class PlatformGameListAdapter() : ListAdapter<Game, PlatformGameListAdapter.ViewHolder>(GenreGameListDiffCallback()) {

    class ViewHolder(private val binding: PlatformGameItemBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bind(game: Game) {
            binding.game = game
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = PlatformGameItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game = getItem(position)
        if (game != null) {
            holder.bind(game)
        }
    }

    override fun getItemCount(): Int {
        val count = super.getItemCount()
        Log.d("rec_test", "getItemCount: count = $count")
        return count
    }



    class GenreGameListDiffCallback : DiffUtil.ItemCallback<Game>() {
        override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
            return oldItem == newItem
        }
    }
}
