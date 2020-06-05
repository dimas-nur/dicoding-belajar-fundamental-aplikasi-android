package com.dnar.dicodingsubmissionbfaa.ui.main.profile.follow

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.dnar.dicodingsubmissionbfaa.R
import com.dnar.dicodingsubmissionbfaa.data.util.ARG_FRAGMENT_TYPE
import com.dnar.dicodingsubmissionbfaa.data.util.ARG_FRAGMENT_VALUE
import com.dnar.dicodingsubmissionbfaa.databinding.FragmentProfileFollowBinding
import com.dnar.dicodingsubmissionbfaa.ui.base.BaseFragment

class ProfileFollowFragment :
    BaseFragment<FragmentProfileFollowBinding, ProfileFollowViewModel>() {

    override var getLayoutId: Int = R.layout.fragment_profile_follow
    override var getViewModel: Class<ProfileFollowViewModel> =
        ProfileFollowViewModel::class.java
    override var title: MutableLiveData<String> = MutableLiveData("Profile")

    companion object {

        fun newInstance(type: String, userId: Int): ProfileFollowFragment {
            val bundle = Bundle().apply {
                putString(ARG_FRAGMENT_TYPE, type)
                putInt(ARG_FRAGMENT_VALUE, userId)
            }

            return ProfileFollowFragment().apply {
                arguments = bundle
            }
        }
    }
}
