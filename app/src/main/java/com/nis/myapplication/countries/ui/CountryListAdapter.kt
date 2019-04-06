package com.nis.myapplication.countries.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nis.myapplication.countries.data.Country
import com.nis.myapplication.countries.ui.CountryListAdapter.CustomViewHolder
import com.nis.myapplication.databinding.ListItemCountryBinding

class CountryListAdapter(private val items: List<Country>):
    RecyclerView.Adapter<CustomViewHolder>(){

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(ListItemCountryBinding.inflate(LayoutInflater.from(parent
            .context), parent, false))
    }

    class CustomViewHolder(private val binding: ListItemCountryBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Country){
            binding.country = item
        }
    }
}