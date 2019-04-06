package com.nis.myapplication.countries.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nis.myapplication.ResponseData
import com.nis.myapplication.countries.data.Countries
import com.nis.myapplication.databinding.CountryListBinding

class CountryListFragment: Fragment() {
    private lateinit var viewModel: CountryListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = CountryListBinding.inflate(inflater, container, false)
        binding.countryList.layoutManager = LinearLayoutManager(context)
        binding.countryList.addItemDecoration(DividerItemDecoration(context,
            DividerItemDecoration.VERTICAL))

        viewModel = ViewModelProviders.of(this).get(CountryListViewModel::class.java)
        viewModel.getCountries().observe(this, Observer { countries ->
            when(countries){
                ResponseData.Progress -> {}
                ResponseData.Error -> {}
                is ResponseData.Data<*> -> {
                    val result = countries.data as Countries
                    binding.countryList.adapter = CountryListAdapter(result.countries)
                }
            }
        })
        return binding.root
    }
}