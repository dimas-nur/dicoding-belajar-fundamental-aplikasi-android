package com.dnar.dicodingsubmissionbfaa.ui.main.profile.follow

import androidx.lifecycle.MutableLiveData
import com.dnar.dicodingsubmissionbfaa.R
import com.dnar.dicodingsubmissionbfaa.databinding.FragmentProfileFollowBinding
import com.dnar.dicodingsubmissionbfaa.ui.base.BaseFragment

class ProfileFollowFragment :
    BaseFragment<FragmentProfileFollowBinding, ProfileFollowViewModel>() {

    override var getLayoutId: Int = R.layout.fragment_profile_follow
    override var getViewModel: Class<ProfileFollowViewModel> =
        ProfileFollowViewModel::class.java
    override var title: MutableLiveData<String> = MutableLiveData("Profile")


}
