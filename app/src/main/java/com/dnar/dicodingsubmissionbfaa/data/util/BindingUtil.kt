package com.dnar.dicodingsubmissionbfaa.data.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter(("imageDrawable"))
fun loadImageDrawable(view: ImageView, id: Int?) {
    id?.let { view.loadEclipseImage(id) }
}
