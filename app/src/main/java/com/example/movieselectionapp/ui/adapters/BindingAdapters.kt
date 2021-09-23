package com.example.movieselectionapp.ui.adapters

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadImage")
fun loadImage(imageView: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(imageView).load(url).into(imageView)
    }
}

fun View.hideIfFalse(boolean: Boolean) {
    if (boolean) this.visibility = View.VISIBLE else this.visibility = View.GONE
}