package com.dnar.dicodingsubmissionbfaa.ui.main.user_detail.followers

import androidx.lifecycle.MutableLiveData
import com.dnar.dicodingsubmissionbfaa.R
import com.dnar.dicodingsubmissionbfaa.databinding.FragmentUserDetailFollowersBinding
import com.dnar.dicodingsubmissionbfaa.ui.base.BaseFragment

class UserDetailFollowersFragment :
    BaseFragment<FragmentUserDetailFollowersBinding, UserDetailFollowersViewModel>() {

    override var getLayoutId: Int = R.layout.fragment_user_detail_followers
    override var getViewModel: Class<UserDetailFollowersViewModel> =
        UserDetailFollowersViewModel::class.java
    override var title: MutableLiveData<String> = MutableLiveData("Profile")


}
