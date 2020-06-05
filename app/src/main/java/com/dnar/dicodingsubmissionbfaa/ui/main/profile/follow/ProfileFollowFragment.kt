package com.dnar.dicodingsubmissionbfaa.ui.main.profile.follow

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dnar.dicodingsubmissionbfaa.R
import com.dnar.dicodingsubmissionbfaa.data.adapter.UserFollowAdapter
import com.dnar.dicodingsubmissionbfaa.data.model.Status
import com.dnar.dicodingsubmissionbfaa.data.util.ARG_FRAGMENT_KEY
import com.dnar.dicodingsubmissionbfaa.data.util.ARG_FRAGMENT_VALUE
import com.dnar.dicodingsubmissionbfaa.databinding.FragmentProfileFollowBinding
import com.dnar.dicodingsubmissionbfaa.ui.base.BaseFragment

class ProfileFollowFragment :
    BaseFragment<FragmentProfileFollowBinding, ProfileFollowViewModel>() {

    private var rvUserFollowAdapter = UserFollowAdapter()

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
                if (getInt(ARG_FRAGMENT_KEY) == 0) {
                    getString(ARG_FRAGMENT_VALUE)?.let { observeFollowers(it) }
                } else {
                    getString(ARG_FRAGMENT_VALUE)?.let { observeFollowing(it) }
                }
            }

            profileFollowRvUserFollow.adapter = rvUserFollowAdapter
        }
    }

    private fun observeFollowers(username: String) {
        mViewModel.getFollowers(username)
            .observe(viewLifecycleOwner, Observer {
                it?.let { status ->
                    when (status.status) {
                        Status.StatusType.LOADING -> {

                        }
                        Status.StatusType.SUCCESS -> {
                            it.data?.let { data ->
                                rvUserFollowAdapter.setList(data)
                            }
                        }
                        Status.StatusType.ERROR -> {
                            Toast.makeText(activity, status.message, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            })
    }

    private fun observeFollowing(username: String) {
        mViewModel.getFollowing(username)
            .observe(viewLifecycleOwner, Observer {
                it?.let { status ->
                    when (status.status) {
                        Status.StatusType.LOADING -> {

                        }
                        Status.StatusType.SUCCESS -> {
                            it.data?.let { data ->
                                rvUserFollowAdapter.setList(data)
                            }
                        }
                        Status.StatusType.ERROR -> {
                            Toast.makeText(activity, status.message, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            })
    }
}
