package com.example.movieselectionapp.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.movieselectionapp.R
import com.example.movieselectionapp.databinding.FragmentMovieFeedBinding
import com.example.movieselectionapp.databinding.FragmentSplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(inflater, container, false)

        lifecycleScope.launch {
            delay(2000)
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToMovieFeedFragment())
        }

        return binding.root
    }

}