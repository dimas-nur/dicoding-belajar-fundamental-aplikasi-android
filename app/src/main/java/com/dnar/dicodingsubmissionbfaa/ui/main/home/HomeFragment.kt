package com.dnar.dicodingsubmissionbfaa.ui.main.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.dnar.dicodingsubmissionbfaa.R
import com.dnar.dicodingsubmissionbfaa.data.adapter.UserAdapter
import com.dnar.dicodingsubmissionbfaa.data.model.User
import com.dnar.dicodingsubmissionbfaa.data.util.changeNavigation
import com.dnar.dicodingsubmissionbfaa.databinding.FragmentHomeBinding
import com.dnar.dicodingsubmissionbfaa.ui.base.BaseFragment

// Home fragment implements dagger fragment
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(), UserAdapter.Listener {

    private val TAG: String = "HomeFragment"

    private var rvUserAdapter = UserAdapter(this)

    override var getLayoutId: Int = R.layout.fragment_home
    override var getViewModel: Class<HomeViewModel> = HomeViewModel::class.java
    override var title: MutableLiveData<String> = MutableLiveData("Github User's")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewBinding.apply {
            homeRvUser.apply {
                adapter = rvUserAdapter
            }
        }
    }

    override fun onUserClickListener(view: View, data: User) {
        val action = HomeFragmentDirections.actionHomeFragmentToUserDetailFragment(data)
        view.changeNavigation(action)
    }
}
