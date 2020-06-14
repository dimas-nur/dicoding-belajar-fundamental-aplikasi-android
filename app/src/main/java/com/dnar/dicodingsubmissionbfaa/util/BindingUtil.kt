package com.dnar.dicodingsubmissionbfaa.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter

// Util for binding into layout; Keyword : Util

/* --- Image View --- */

// Function : for load image from Url into ImageView
@BindingAdapter(("imageUrl"))
fun loadImageUrl(view: ImageView, url: String?) {
    url?.let { view.loadEclipseImage(url) }
}

// Function : for load image from id into ImageView
@BindingAdapter(("drawableId"))
fun loadImageId(view: ImageView, id: Int?) {
    id?.let { view.loadImage(id) }
}
