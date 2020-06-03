package com.dnar.dicodingsubmissionbfaa.ui.main.user_detail

import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.dnar.dicodingsubmissionbfaa.R
import com.dnar.dicodingsubmissionbfaa.databinding.FragmentUserDetailBinding
import com.dnar.dicodingsubmissionbfaa.ui.base.BaseFragment

class UserDetailFragment : BaseFragment<FragmentUserDetailBinding, UserDetailViewModel>() {

    override var getLayoutId: Int = R.layout.fragment_user_detail
    override var getViewModel: Class<UserDetailViewModel> = UserDetailViewModel::class.java
    override var title: MutableLiveData<String> = MutableLiveData("Profile")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewBinding.apply {
            arguments?.let {
                user = UserDetailFragmentArgs.fromBundle(it).user
            }
        }
    }
}
