package com.dnar.dicodingsubmissionbfaa.ui.main.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dnar.dicodingsubmissionbfaa.R
import com.dnar.dicodingsubmissionbfaa.data.adapter.UserSearchAdapter
import com.dnar.dicodingsubmissionbfaa.data.model.Status
import com.dnar.dicodingsubmissionbfaa.data.model.UserSearch
import com.dnar.dicodingsubmissionbfaa.data.util.changeNavigation
import com.dnar.dicodingsubmissionbfaa.databinding.FragmentHomeBinding
import com.dnar.dicodingsubmissionbfaa.ui.base.BaseFragment

// Home fragment implements dagger fragment
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(),
    UserSearchAdapter.Listener {

    private val TAG: String = "HomeFragment"

    private var rvUserAdapter = UserSearchAdapter(this)

    override var getLayoutId: Int = R.layout.fragment_home
    override var getViewModel: Class<HomeViewModel> = HomeViewModel::class.java
    override var title: MutableLiveData<String> = MutableLiveData("Github User's")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewBinding.apply {
            homeRvUser.apply {
                adapter = rvUserAdapter
            }

            homeBtnSearch.setOnClickListener {
                observeUserSearch(homeEtSearch.text.toString())
            }
        }
    }

    private fun observeUserSearch(keyword: String) {
        mViewModel.getUserSearch(keyword)
            .observe(viewLifecycleOwner, Observer {
                it?.let { status ->
                    when (status.status) {
                        Status.StatusType.LOADING -> {

                        }
                        Status.StatusType.SUCCESS -> {
                            it.data?.let { data ->
                                if (data.total_count > 0) {
                                    rvUserAdapter.setList(data.items)
                                } else {

                                }
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

    override fun onUserClickListener(view: View, data: UserSearch) {
        val action = HomeFragmentDirections.actionHomeFragmentToUserDetailFragment(data)
        view.changeNavigation(action)
    }
}
