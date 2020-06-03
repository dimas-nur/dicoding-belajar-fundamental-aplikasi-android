package com.dnar.dicodingsubmissionbfaa.data.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter

// Util for binding into layout; Keyword : Util

/* --- Image View --- */
@BindingAdapter(("imageName"))
fun loadImageDrawable(view: ImageView, name: String?) {
    name?.let { view.loadEclipseImage(name) }
}
