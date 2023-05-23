package com.ltu.m7019e.v23.themoviedb.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import com.ltu.m7019e.v23.themoviedb.R
import com.ltu.m7019e.v23.themoviedb.databinding.PlatformItemListBinding
import com.ltu.m7019e.v23.themoviedb.model.Game
import com.ltu.m7019e.v23.themoviedb.model.Platform
import com.ltu.m7019e.v23.themoviedb.viewmodel.SecondFragmentViewModel

class PlatformListAdapter() : ListAdapter<Platform, PlatformListAdapter.ViewHolder>(GenreListDiffCallback()) {

    class ViewHolder(private val binding: PlatformItemListBinding) : RecyclerView.ViewHolder(binding.root) {

        private val genreGameListRV: RecyclerView


        //val platformGameListClickListener: PlatformGameListClickListener
        val platformGameListAdapter = PlatformGameListAdapter()

        init {
            val context = itemView.context
            genreGameListRV = itemView.findViewById(R.id.genre_game_list_rv)
            genreGameListRV?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            //platformGameListAdapter = PlatformGameListAdapter(platformGameListClickListener)
            genreGameListRV.adapter = platformGameListAdapter
        }

        fun bind(platform: Platform) {
            binding.genreName.text = platform.platform?.name
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = PlatformItemListBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val platform = getItem(position)
        if (platform != null) {
            holder.platformGameListAdapter.submitList(platform.platform?.gameList)
            holder.bind(platform)

        }
    }

    override fun getItemCount(): Int {
        val count = super.getItemCount()
        Log.d("rec_test", "getItemCount: count = $count")
        return count
    }

    class GenreListDiffCallback : DiffUtil.ItemCallback<Platform>() {
        override fun areItemsTheSame(oldItem: Platform, newItem: Platform): Boolean {
            return oldItem.platform?.name == newItem.platform?.name
        }

        override fun areContentsTheSame(oldItem: Platform, newItem: Platform): Boolean {
            return oldItem == newItem
        }

    }
}
