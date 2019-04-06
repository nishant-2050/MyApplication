package com.nis.myapplication.tmdb.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nis.myapplication.ResponseData
import com.nis.myapplication.databinding.MovieListBinding
import com.nis.myapplication.tmdb.data.TmdbMovieResponse

class TmdbListFragment: Fragment() {
    private lateinit var viewModel: TmdbListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = MovieListBinding.inflate(inflater, container, false)
        binding.movieList.layoutManager = LinearLayoutManager(context)

        viewModel = ViewModelProviders.of(this).get(TmdbListViewModel::class.java)

        viewModel.getAllMovies().observe(this, Observer {movies ->
            when(movies) {
                ResponseData.Progress -> {
                    binding.idProgress.visibility = View.VISIBLE
                    binding.movieList.visibility = View.INVISIBLE
                }
                ResponseData.Error -> {
                    binding.idProgress.visibility = View.INVISIBLE
                    binding.movieList.visibility = View.VISIBLE
                }
                is ResponseData.Data<*> -> {
                    binding.idProgress.visibility = View.INVISIBLE
                    binding.movieList.visibility = View.VISIBLE
                    val data = movies.data as TmdbMovieResponse
                    binding.movieList.adapter = TmdbListAdapter(data.results, object:
                        TmdbListAdapter.OnItemClickListener {
                        override fun invoke(position: Int) {
                            var action = TmdbListFragmentDirections
                                .actionTmdbListFragmentToTmdbDetailFragment()
                            action.setSelectedId(position)
                            findNavController().navigate(action)
                        }
                    })
                }
            }
        })

        return binding.root
    }
}