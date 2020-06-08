package com.dnar.dicodingsubmissionbfaa.ui.main.profile.follow

import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import cn.pedant.SweetAlert.SweetAlertDialog
import com.dnar.dicodingsubmissionbfaa.R
import com.dnar.dicodingsubmissionbfaa.data.adapter.UserFollowAdapter
import com.dnar.dicodingsubmissionbfaa.data.model.Status
import com.dnar.dicodingsubmissionbfaa.data.model.UserSearch
import com.dnar.dicodingsubmissionbfaa.data.util.*
import com.dnar.dicodingsubmissionbfaa.databinding.FragmentProfileFollowBinding
import com.dnar.dicodingsubmissionbfaa.ui.base.BaseFragment
import com.dnar.dicodingsubmissionbfaa.ui.main.MainActivity
import com.dnar.dicodingsubmissionbfaa.ui.main.home.HomeFragmentDirections
import com.dnar.dicodingsubmissionbfaa.ui.main.profile.ProfileFragmentDirections

class ProfileFollowFragment : BaseFragment<FragmentProfileFollowBinding, ProfileFollowViewModel>(),
    UserFollowAdapter.Listener {

    private lateinit var mDialog: SweetAlertDialog

    private var rvUserFollowAdapter = UserFollowAdapter(this)

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

        setContent(1)

        mDialog = (activity as MainActivity).mDialog

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
        mViewBinding.apply {
            profileFollowProgressBar.show()

            mViewModel.getFollowers(username)
                .observe(viewLifecycleOwner, Observer {
                    it?.let { status ->
                        when (status.status) {
                            Status.StatusType.LOADING -> {
                            }
                            Status.StatusType.SUCCESS -> {
                                it.data?.let { data ->
                                    mViewBinding.profileFollowProgressBar.hide()

                                    rvUserFollowAdapter.setList(data)
                                    if (data.isNotEmpty()) {
                                        setContent(1)
                                        rvUserFollowAdapter.setList(data)
                                    } else {
                                        setContent(5)
                                    }
                                }
                            }
                            Status.StatusType.ERROR -> {
                                mViewBinding.profileFollowProgressBar.hide()

                                showDialogWarning(mDialog, status.message ?: "Error", null)
                                setContent(2)
                            }
                        }
                    }
                })
        }
    }

    private fun observeFollowing(username: String) {
        mViewBinding.apply {
            profileFollowProgressBar.show()

            mViewModel.getFollowing(username)
                .observe(viewLifecycleOwner, Observer {
                    it?.let { status ->
                        when (status.status) {
                            Status.StatusType.LOADING -> {
                            }
                            Status.StatusType.SUCCESS -> {
                                it.data?.let { data ->
                                    mViewBinding.profileFollowProgressBar.hide()

                                    rvUserFollowAdapter.setList(data)
                                    if (data.isNotEmpty()) {
                                        setContent(1)
                                        rvUserFollowAdapter.setList(data)
                                    } else {
                                        setContent(6)
                                    }
                                }
                            }
                            Status.StatusType.ERROR -> {
                                mViewBinding.profileFollowProgressBar.hide()

                                showDialogWarning(mDialog, status.message ?: "Error", null)
                                setContent(2)
                            }
                        }
                    }
                })
        }
    }

    override fun onUserClickListener(view: View, data: UserSearch) {
        val action = ProfileFragmentDirections.actionUserDetailFragmentSelf(data.login)
        view.changeNavigation(action)
    }
}
