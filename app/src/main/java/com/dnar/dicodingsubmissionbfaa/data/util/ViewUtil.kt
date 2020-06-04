package com.dnar.dicodingsubmissionbfaa.data.util

import android.view.View
import android.widget.ImageView
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.dnar.dicodingsubmissionbfaa.R

// Util for view; Keyword : Util

/* --- Image View --- */
fun ImageView.loadEclipseImage(url: String?) {
    val option = RequestOptions()
        .error(R.drawable.ic_launcher_background)
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .circleCrop()

    Glide.with(context)
        .setDefaultRequestOptions(option)
        .load(url)
        .into(this)
}

/* --- Navigation --- */
fun View.changeNavigation(direction: NavDirections) {
    Navigation.findNavController(this).navigate(direction)
}
