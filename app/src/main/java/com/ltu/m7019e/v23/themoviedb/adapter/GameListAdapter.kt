package com.ltu.m7019e.v23.themoviedb.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ltu.m7019e.v23.themoviedb.databinding.GameListItemBinding
import com.ltu.m7019e.v23.themoviedb.model.Game

class GameListAdapter(private val gameClickListener: GameListClickListener) :  ListAdapter<Game, GameListAdapter.ViewHolder>(GameListDiffCallback()){
    class ViewHolder(private var binding: GameListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(game: Game, gameClickListener: GameListClickListener) {
            binding.game = game
            binding.clickListener = gameClickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup) : ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = GameListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), gameClickListener)
    }
}

class GameListDiffCallback : DiffUtil.ItemCallback<Game>() {
    override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
        return oldItem.appid == newItem.appid
    }

    override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
        return oldItem == newItem
    }

}

class GameListClickListener(val clickListener: (game: Game) -> Unit) {
    fun onClick(game: Game) = clickListener(game)
}