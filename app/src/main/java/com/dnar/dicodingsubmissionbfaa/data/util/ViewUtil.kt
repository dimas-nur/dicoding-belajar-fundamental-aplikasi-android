package com.dnar.dicodingsubmissionbfaa.data.util

import android.view.View
import android.widget.ImageView
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.dnar.dicodingsubmissionbfaa.R


// Util for view; Keyword : Util

/* --- All --- */

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}


/* --- Image View --- */
fun ImageView.loadImage(id: Int?) {
    id?.let { this.setImageResource(it) }
}

fun ImageView.loadEclipseImage(url: String?) {
    val loader = CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 40f
        start()
    }

    val option = RequestOptions()
        .error(R.drawable.img_picture)
        .placeholder(loader)
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .circleCrop()

    try {
        Glide.with(context)
            .setDefaultRequestOptions(option)
            .load(url)
            .into(this)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}


/* --- Navigation --- */
fun View.changeNavigation(direction: NavDirections) {
    Navigation.findNavController(this).navigate(direction)
}
