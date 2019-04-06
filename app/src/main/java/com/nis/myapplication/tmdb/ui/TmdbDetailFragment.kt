package com.nis.myapplication.tmdb.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.nis.myapplication.ResponseData
import com.nis.myapplication.databinding.MovieDetailsBinding
import com.nis.myapplication.tmdb.data.TmdbMovieResponse

class TmdbDetailFragment: Fragment() {
    private lateinit var viewModel: TmdbListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = MovieDetailsBinding.inflate(inflater, container, false)
        val selectedId = TmdbDetailFragmentArgs.fromBundle(arguments!!).selectedId
        val id = selectedId

        viewModel = ViewModelProviders.of(this).get(TmdbListViewModel::class.java)
        viewModel.getAllMovies().observe(this, Observer { movies->
            if(movies is ResponseData.Data<*>){
                val data = movies.data as TmdbMovieResponse
                val selectedMovie = data.results[selectedId]
                binding.movie = selectedMovie
            }
        })
        return binding.root
    }
}