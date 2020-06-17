package com.dnar.consumerapp.ui.main.home

import androidx.lifecycle.MutableLiveData
import com.dnar.consumerapp.R
import com.dnar.consumerapp.databinding.FragmentHomeBinding
import com.dnar.consumerapp.ui.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override var getLayoutId: Int = R.layout.fragment_home
    override var getViewModel: Class<HomeViewModel> = HomeViewModel::class.java
    override var title: MutableLiveData<String> = MutableLiveData("Home")

}