package com.dnar.dicodingsubmissionbfaa.ui.main.home

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData

import com.dnar.dicodingsubmissionbfaa.R
import com.dnar.dicodingsubmissionbfaa.databinding.FragmentHomeBinding
import com.dnar.dicodingsubmissionbfaa.ui.base.BaseFragment

// Home fragment implements dagger fragment
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override var getLayoutId: Int = R.layout.fragment_home
    override var getViewModel: Class<HomeViewModel> = HomeViewModel::class.java
    override var title: MutableLiveData<String> = MutableLiveData("Github User's")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}
