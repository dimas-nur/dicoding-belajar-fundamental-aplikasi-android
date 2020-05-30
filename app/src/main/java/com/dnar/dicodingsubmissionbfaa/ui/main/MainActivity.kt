package com.dnar.dicodingsubmissionbfaa.ui.main

import android.os.Bundle
import com.dnar.dicodingsubmissionbfaa.R
import com.dnar.dicodingsubmissionbfaa.databinding.ActivityMainBinding
import com.dnar.dicodingsubmissionbfaa.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    private val TAG: String = "MainActivity"

    override var getLayoutId: Int = R.layout.activity_main
    override var getViewModel: Class<MainViewModel> = MainViewModel::class.java

    fun changeToolbarTitle(title: String) {
        mViewBinding.mainToolbarTittle.text = title
    }
}
