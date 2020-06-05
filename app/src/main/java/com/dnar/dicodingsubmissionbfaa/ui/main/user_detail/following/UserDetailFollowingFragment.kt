package com.dnar.dicodingsubmissionbfaa.ui.main.user_detail.following

import androidx.lifecycle.MutableLiveData
import com.dnar.dicodingsubmissionbfaa.R
import com.dnar.dicodingsubmissionbfaa.databinding.FragmentUserDetailFollowingBinding
import com.dnar.dicodingsubmissionbfaa.ui.base.BaseFragment

class UserDetailFollowingFragment :
    BaseFragment<FragmentUserDetailFollowingBinding, UserDetailFollowingViewModel>() {

    override var getLayoutId: Int = R.layout.fragment_user_detail_following
    override var getViewModel: Class<UserDetailFollowingViewModel> =
        UserDetailFollowingViewModel::class.java
    override var title: MutableLiveData<String> = MutableLiveData("Profile")

}