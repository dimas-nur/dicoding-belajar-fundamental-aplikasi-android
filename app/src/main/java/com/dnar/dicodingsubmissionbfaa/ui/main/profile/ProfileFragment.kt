package com.dnar.dicodingsubmissionbfaa.ui.main.profile

import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import cn.pedant.SweetAlert.SweetAlertDialog
import com.dnar.dicodingsubmissionbfaa.R
import com.dnar.dicodingsubmissionbfaa.data.model.Status
import com.dnar.dicodingsubmissionbfaa.data.util.hide
import com.dnar.dicodingsubmissionbfaa.data.util.show
import com.dnar.dicodingsubmissionbfaa.data.util.showDialogWarning
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

        setContent(1)

        mDialog = (activity as MainActivity).mDialog

        mViewBinding.apply {
            arguments?.let {
                val username = ProfileFragmentArgs.fromBundle(it).username
                observeDetail(username)

                profileViewPager.adapter =
                    ProfileSectionsPagerAdapter(childFragmentManager, username)
                profileTabLayout.setupWithViewPager(profileViewPager)
            }

            profileBtnBack.setOnClickListener {
                activity?.onBackPressed()
            }
        }
    }

    private fun observeDetail(username: String) {
        mViewBinding.apply {
            profileProgressBar.show()

            mViewModel.getDetail(username)
                .observe(viewLifecycleOwner, Observer {
                    it?.let { status ->
                        when (status.status) {
                            Status.StatusType.LOADING -> {
                            }
                            Status.StatusType.SUCCESS -> {
                                profileProgressBar.hide()

                                it.data?.let { data ->
                                    setContent(1)
                                    user = data
                                }
                            }
                            Status.StatusType.ERROR -> {
                                profileProgressBar.hide()

                                showDialogWarning(mDialog, status.message ?: "Error", null)
                                setContent(2)
                            }
                        }
                    }
                })
        }
    }
}
