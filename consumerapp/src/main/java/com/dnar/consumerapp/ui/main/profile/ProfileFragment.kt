package com.dnar.consumerapp.ui.main.profile

import androidx.lifecycle.MutableLiveData
import com.dnar.consumerapp.R
import com.dnar.consumerapp.databinding.FragmentProfileBinding
import com.dnar.consumerapp.ui.base.BaseFragment

class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>() {

    override var getLayoutId: Int = R.layout.fragment_profile
    override var getViewModel: Class<ProfileViewModel> = ProfileViewModel::class.java
    override var title: MutableLiveData<String> = MutableLiveData("Profile")

}