package com.dnar.dicodingsubmissionbfaa.data.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.dnar.dicodingsubmissionbfaa.R

/* --- Image View --- */
fun ImageView.loadEclipseImage(name: String) {
    val option = RequestOptions()
        .error(R.drawable.ic_launcher_background)
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .circleCrop()

    Glide.with(context)
        .setDefaultRequestOptions(option)
        .load(
            resources.getIdentifier(name, "drawable", context.packageName)
        )
        .into(this)
}
