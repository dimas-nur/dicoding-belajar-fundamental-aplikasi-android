package com.dnar.consumerapp.ui.base

import android.os.Bundle
import android.view.Window
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dnar.consumerapp.di.viewmodel.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

// Base Activity for implements DaggerAppCompatActivity; Keyword : Base
abstract class BaseActivity<T : ViewDataBinding, VM : ViewModel> : DaggerAppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    lateinit var mViewBinding: T
    private lateinit var mViewModel: VM

    abstract var getLayoutId: Int
    abstract var getViewModel: Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)

        // Set View Binding & View Model
        mViewBinding = DataBindingUtil.setContentView(this, getLayoutId)
        mViewModel = ViewModelProvider(this, factory)[getViewModel]

        setContentView(mViewBinding.root)
    }
}