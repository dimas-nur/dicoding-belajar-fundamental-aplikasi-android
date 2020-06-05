package com.dnar.dicodingsubmissionbfaa.ui.main.user_detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dnar.dicodingsubmissionbfaa.R
import com.dnar.dicodingsubmissionbfaa.data.model.Status
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
                val username = UserDetailFragmentArgs.fromBundle(it).username
                observeDetail(username)
            }
        }
    }

    private fun observeDetail(username: String) {
        mViewModel.getDetail(username)
            .observe(viewLifecycleOwner, Observer {
                it?.let { status ->
                    when (status.status) {
                        Status.StatusType.LOADING -> {

                        }
                        Status.StatusType.SUCCESS -> {
                            it.data?.let { data ->
                                mViewBinding.user = data
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
