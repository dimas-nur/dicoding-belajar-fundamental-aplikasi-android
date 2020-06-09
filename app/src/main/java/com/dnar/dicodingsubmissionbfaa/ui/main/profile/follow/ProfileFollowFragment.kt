package com.dnar.dicodingsubmissionbfaa.ui.main.profile.follow

import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import cn.pedant.SweetAlert.SweetAlertDialog
import com.dnar.dicodingsubmissionbfaa.R
import com.dnar.dicodingsubmissionbfaa.data.adapter.UserFollowAdapter
import com.dnar.dicodingsubmissionbfaa.data.model.Status
import com.dnar.dicodingsubmissionbfaa.data.model.UserSearch
import com.dnar.dicodingsubmissionbfaa.data.util.*
import com.dnar.dicodingsubmissionbfaa.databinding.FragmentProfileFollowBinding
import com.dnar.dicodingsubmissionbfaa.ui.base.BaseFragment
import com.dnar.dicodingsubmissionbfaa.ui.main.MainActivity
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

        // Function : for create new instance ProfileFollowFragment and pass value
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

        // Set condition PlaceholderView
        setContentPlaceholder(1)

        // Set mDialog to get dialog from MainActivity
        mDialog = (activity as MainActivity).mDialog

        // Configure ViewBinding
        mViewBinding.apply {

            // Get data from arguments
            arguments?.apply {
                if (getInt(ARG_FRAGMENT_KEY) == 0) {

                    // Observe DataFollowers and set data into RecyclerViewAdapter
                    getString(ARG_FRAGMENT_VALUE)?.let { observeFollowers(it) }
                } else {

                    // Observe DataFollowing and set data into RecyclerViewAdapter
                    getString(ARG_FRAGMENT_VALUE)?.let { observeFollowing(it) }
                }
            }

            // Set RecyclerViewAdapter
            profileFollowRvUserFollow.adapter = rvUserFollowAdapter
        }
    }

    // Function : for observe data user followers
    private fun observeFollowers(username: String) {
        mViewBinding.apply {

            // Show ProgressBar
            profileFollowProgressBar.show()

            mViewModel.getFollowers(username)
                .observe(viewLifecycleOwner, Observer {
                    it?.let { status ->
                        when (status.status) {
                            Status.StatusType.SUCCESS -> {
                                it.data?.let { data ->

                                    // Hide ProgressBar and set data in RecyclerViewAdapter
                                    mViewBinding.profileFollowProgressBar.hide()

                                    rvUserFollowAdapter.setList(data)
                                    if (data.isNotEmpty()) {
                                        // When data is not empty
                                        setContentPlaceholder(1)
                                        rvUserFollowAdapter.setList(data)
                                    } else {
                                        // When data is empty
                                        setContentPlaceholder(5)
                                    }
                                }
                            }
                            Status.StatusType.ERROR -> {

                                // Hide ProgressBar and show warning dialog
                                mViewBinding.profileFollowProgressBar.hide()

                                showDialogWarning(mDialog, status.message ?: "Error", null)
                                setContentPlaceholder(2)
                            }
                        }
                    }
                })
        }
    }

    // Function : for observe data user followers
    private fun observeFollowing(username: String) {
        mViewBinding.apply {

            // Show ProgressBar
            profileFollowProgressBar.show()

            mViewModel.getFollowing(username)
                .observe(viewLifecycleOwner, Observer {
                    it?.let { status ->
                        when (status.status) {
                            Status.StatusType.SUCCESS -> {
                                it.data?.let { data ->

                                    // Hide ProgressBar and set data in RecyclerViewAdapter
                                    mViewBinding.profileFollowProgressBar.hide()

                                    rvUserFollowAdapter.setList(data)
                                    if (data.isNotEmpty()) {
                                        // When data is not empty
                                        setContentPlaceholder(1)
                                        rvUserFollowAdapter.setList(data)
                                    } else {
                                        // When data is empty
                                        setContentPlaceholder(6)
                                    }
                                }
                            }
                            Status.StatusType.ERROR -> {

                                // Hide ProgressBar and show warning dialog
                                mViewBinding.profileFollowProgressBar.hide()

                                showDialogWarning(mDialog, status.message ?: "Error", null)
                                setContentPlaceholder(2)
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
