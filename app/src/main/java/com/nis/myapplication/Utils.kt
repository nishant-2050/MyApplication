package com.nis.myapplication

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

sealed class ResponseData {
    object Progress : ResponseData()
    object Error : ResponseData()
    data class Data<T>(val data: T): ResponseData()
}

@BindingAdapter("urlImage")
fun bindImageFromUrl(view: ImageView, imageUrl: String?){
    if(!imageUrl.isNullOrEmpty()){
        Glide.with(view.context)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}