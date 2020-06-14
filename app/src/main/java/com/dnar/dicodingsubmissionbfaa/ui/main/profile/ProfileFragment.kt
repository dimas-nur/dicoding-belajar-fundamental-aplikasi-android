package com.dnar.dicodingsubmissionbfaa.ui.main.profile

import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import cn.pedant.SweetAlert.SweetAlertDialog
import com.dnar.dicodingsubmissionbfaa.R
import com.dnar.dicodingsubmissionbfaa.data.model.Status
import com.dnar.dicodingsubmissionbfaa.util.hide
import com.dnar.dicodingsubmissionbfaa.util.show
import com.dnar.dicodingsubmissionbfaa.util.showDialogWarning
import com.dnar.dicodingsubmissionbfaa.databinding.FragmentProfileBinding
import com.dnar.dicodingsubmissionbfaa.ui.base.BaseFragment
import com.dnar.dicodingsubmissionbfaa.ui.main.MainActivity

class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>() {

    private lateinit var mDialog: SweetAlertDialog

    override var getLayoutId: Int = R.layout.fragment_profile
    override var getViewModel: Class<ProfileViewModel> = ProfileViewModel::class.java
    override var title: MutableLiveData<String> = MutableLiveData("Profile")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set condition PlaceholderView
        setContentPlaceholder(1)

        // Set mDialog to get dialog from MainActivity
        mDialog = (activity as MainActivity).mDialog

        // Configure ViewBinding
        mViewBinding.apply {

            // Get data from arguments
            arguments?.let {
                val username = ProfileFragmentArgs.fromBundle(it).username
                observeDetail(username)

                // Set ViewPagerAdapter
                profileViewPager.adapter =
                    ProfileSectionsPagerAdapter(
                        childFragmentManager,
                        this@ProfileFragment,
                        username
                    )
                profileTabLayout.setupWithViewPager(profileViewPager)
            }

            // Set button BackToolbar onClickListener
            profileBtnBack.setOnClickListener {
                activity?.onBackPressed()
            }
        }
    }

    // Function : for observe data user detail from api
    private fun observeDetail(username: String) {
        mViewBinding.apply {

            // Show ProgressBar
            profileProgressBar.show()

            mViewModel.getDetail(username)
                .observe(viewLifecycleOwner, Observer {
                    it?.let { status ->
                        when (status.status) {
                            Status.StatusType.SUCCESS -> {

                                // Hide ProgressBar and set data in ViewBinding
                                profileProgressBar.hide()

                                it.data?.let { data ->
                                    setContentPlaceholder(1)
                                    user = data
                                }
                            }
                            Status.StatusType.ERROR -> {

                                // Hide ProgressBar and show warning dialog
                                profileProgressBar.hide()

                                showDialogWarning(mDialog, status.message ?: "Error", null)
                                setContentPlaceholder(2)
                            }
                        }
                    }
                })
        }
    }
}
