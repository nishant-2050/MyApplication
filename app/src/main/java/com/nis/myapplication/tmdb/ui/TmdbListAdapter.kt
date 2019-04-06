package com.nis.myapplication.tmdb.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nis.myapplication.databinding.ListItemMovieBinding
import com.nis.myapplication.tmdb.data.TmdbMovie
import com.nis.myapplication.tmdb.ui.TmdbListAdapter.CustomViewHolder

class TmdbListAdapter(private val items: List<TmdbMovie>,
    private val itemClick: OnItemClickListener): RecyclerView.Adapter<CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding = ListItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent,
            false)
        return CustomViewHolder(binding, itemClick)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(items[position], position)
    }

    class CustomViewHolder(private val binding: ListItemMovieBinding,
        private val itemClick: OnItemClickListener): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TmdbMovie, position: Int){
            binding.movie = item
            binding.root.setOnClickListener {
                itemClick(position)
            }
        }
    }

    interface OnItemClickListener {
        operator fun invoke(position: Int)
    }
}