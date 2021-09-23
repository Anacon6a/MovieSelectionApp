package com.example.movieselectionapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieselectionapp.data.model.MovieModel
import com.example.movieselectionapp.databinding.MovieDataBinding

class MovieRecyclerAdapter : PagingDataAdapter<MovieModel, MovieRecyclerAdapter.MovieViewHolder>(
    Companion
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = MovieDataBinding.inflate(
            layoutInflater,
            parent,
            false
        )
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position) ?: return
        holder.bind(movie)
    }

    companion object : DiffUtil.ItemCallback<MovieModel>() {
        override fun areItemsTheSame(
            oldItem: MovieModel, newItem: MovieModel
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: MovieModel, newItem: MovieModel
        ): Boolean {
            return oldItem == newItem
        }
    }

    inner class MovieViewHolder(
        private val binding: MovieDataBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieModel) {
            binding.movie = movie
        }
    }
}