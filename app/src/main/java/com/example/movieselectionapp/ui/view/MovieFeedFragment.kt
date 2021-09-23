package com.example.movieselectionapp.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.movieselectionapp.R
import com.example.movieselectionapp.data.remote.api.RestApi
import com.example.movieselectionapp.databinding.ActivityMainBinding
import com.example.movieselectionapp.databinding.FragmentMovieFeedBinding
import com.example.movieselectionapp.extensions.kodeinViewModel
import com.example.movieselectionapp.ui.adapters.MovieRecyclerAdapter
import com.example.movieselectionapp.ui.adapters.hideIfFalse
import com.example.movieselectionapp.ui.viewmodel.MovieFeedViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein


class MovieFeedFragment : Fragment(), KodeinAware {
    override val kodein by kodein()
    private val viewModel: MovieFeedViewModel by kodeinViewModel()
    private lateinit var binding: FragmentMovieFeedBinding
    private val movieRecyclerAdapter by lazy {
        MovieRecyclerAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieFeedBinding.inflate(inflater, container, false)
        binding.movieFeedViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        statusLoading()
        setAdaptersRecycler()
        getMovies()

        return binding.root
    }

    private fun statusLoading() {
        lifecycleScope.launch {
            movieRecyclerAdapter.loadStateFlow.collectLatest {
                binding.progress.hideIfFalse(it.source.refresh is LoadState.Loading)
            }
        }
    }

    private fun setAdaptersRecycler() {
        lifecycleScope.launch {
            binding.movieRecycler.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = movieRecyclerAdapter
            }
        }
    }

    private fun getMovies() {
        lifecycleScope.launch {
            viewModel.getMovies().collect {
                movieRecyclerAdapter.submitData(it)
            }
        }
    }

}