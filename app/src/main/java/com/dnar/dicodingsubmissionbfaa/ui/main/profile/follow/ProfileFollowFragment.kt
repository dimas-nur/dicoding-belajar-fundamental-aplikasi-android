package com.dnar.dicodingsubmissionbfaa.ui.main.profile.follow

import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.dnar.dicodingsubmissionbfaa.R
import com.dnar.dicodingsubmissionbfaa.data.util.ARG_FRAGMENT_KEY
import com.dnar.dicodingsubmissionbfaa.data.util.ARG_FRAGMENT_VALUE
import com.dnar.dicodingsubmissionbfaa.databinding.FragmentProfileFollowBinding
import com.dnar.dicodingsubmissionbfaa.ui.base.BaseFragment

class ProfileFollowFragment :
    BaseFragment<FragmentProfileFollowBinding, ProfileFollowViewModel>() {

    private val TAG: String = "ProfileFollowFragment"

    override var getLayoutId: Int = R.layout.fragment_profile_follow
    override var getViewModel: Class<ProfileFollowViewModel> =
        ProfileFollowViewModel::class.java
    override var title: MutableLiveData<String> = MutableLiveData("Profile")

    companion object {

        fun newInstance(key: Int, username: String): ProfileFollowFragment {
            val bundle = Bundle().apply {
                putInt(ARG_FRAGMENT_KEY, key)
                putString(ARG_FRAGMENT_VALUE, username)
            }

            return ProfileFollowFragment().apply {
                arguments = bundle
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewBinding.apply {
            arguments?.apply {
                tvUsername.text = "${getInt(ARG_FRAGMENT_KEY)} - ${getString(ARG_FRAGMENT_VALUE)}"
            }
        }
    }
}
