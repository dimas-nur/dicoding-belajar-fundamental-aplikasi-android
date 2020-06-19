package com.dnar.dicodingsubmissionbfaa.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.dnar.dicodingsubmissionbfaa.R


// Util for view; Keyword : Util

/* --- All --- */

// Function : for set visibility View to VISIBLE
fun View.show() {
    this.visibility = View.VISIBLE
}

// Function : for set visibility View to GONE
fun View.hide() {
    this.visibility = View.GONE
}


/* --- Image View --- */

// Function : for load image from Id into ImageView
fun ImageView.loadImage(id: Int?) {
    id?.let { this.setImageResource(it) }
}

// Function : for load image from Url into ImageView and crop into circle
fun ImageView.loadEclipseImage(url: String?) {
    val loader = CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 40f
        start()
    }

    val option = RequestOptions()
        .error(R.drawable.img_picture)
        .placeholder(loader)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
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

// Function : for change navigation of NavController
fun View.changeNavigation(direction: NavDirections) {
    Navigation.findNavController(this).navigate(direction)
}

fun String.toBitmap(context: Context): Bitmap {
    var bitmap: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.img_picture)

    val option = RequestOptions()
        .error(R.drawable.img_picture)
        .diskCacheStrategy(DiskCacheStrategy.ALL)

    try {
        Glide.with(context)
            .setDefaultRequestOptions(option)
            .asBitmap()
            .load(this)
            .into(object : CustomTarget<Bitmap>() {
                override fun onLoadCleared(placeholder: Drawable?) {}
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    bitmap = resource
                }
            })
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return bitmap
}
