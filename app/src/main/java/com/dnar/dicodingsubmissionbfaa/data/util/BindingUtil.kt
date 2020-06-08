package com.dnar.dicodingsubmissionbfaa.data.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter

// Util for binding into layout; Keyword : Util

/* --- Image View --- */
@BindingAdapter(("imageUrl"))
fun loadImageUrl(view: ImageView, url: String?) {
    url?.let { view.loadEclipseImage(url) }
}

@BindingAdapter(("drawableId"))
fun loadImageId(view: ImageView, id: Int?) {
    id?.let { view.loadImage(id) }
}
