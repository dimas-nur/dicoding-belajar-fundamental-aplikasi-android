package com.dnar.consumerapp.ui.main

import com.dnar.consumerapp.R
import com.dnar.consumerapp.databinding.ActivityMainBinding
import com.dnar.consumerapp.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override var getLayoutId: Int = R.layout.activity_main
    override var getViewModel: Class<MainViewModel> = MainViewModel::class.java


    // Function : for set toolbar
    private fun setToolbar() {
        mViewBinding.apply {
            setSupportActionBar(mainToolbar)
            mainToolbarTittle.text = getString(R.string.app_name)
        }

        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    // Function : for change tittle toolbar
    fun changeToolbarTitle(title: String) {
        mViewBinding.mainToolbarTittle.text = title
    }

}
