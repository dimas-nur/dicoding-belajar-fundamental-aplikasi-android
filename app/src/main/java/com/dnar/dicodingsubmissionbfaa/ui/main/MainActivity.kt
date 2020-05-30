package com.dnar.dicodingsubmissionbfaa.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dnar.dicodingsubmissionbfaa.R
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
