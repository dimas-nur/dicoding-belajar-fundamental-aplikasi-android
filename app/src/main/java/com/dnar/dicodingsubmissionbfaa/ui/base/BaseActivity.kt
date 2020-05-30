package com.dnar.dicodingsubmissionbfaa.ui.base

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dimasnur.daggerpractice.di.viewmodel.ViewModelProviderFactory
import com.dnar.dicodingsubmissionbfaa.databinding.ActivityMainBinding
import com.dnar.dicodingsubmissionbfaa.databinding.ActivityMainBinding.inflate
import com.dnar.dicodingsubmissionbfaa.ui.main.MainActivity
import com.dnar.dicodingsubmissionbfaa.ui.main.MainViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<T: ViewDataBinding, VM: ViewModel> : DaggerAppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    lateinit var mViewBinding: T
    lateinit var mViewModel: VM

    abstract var getLayoutId: Int
    abstract var getViewModel: Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set View Binding & View Model
        mViewBinding = DataBindingUtil.setContentView(this, getLayoutId)
        mViewModel = ViewModelProvider(this, factory)[getViewModel]

        setContentView(mViewBinding.root)
    }
}