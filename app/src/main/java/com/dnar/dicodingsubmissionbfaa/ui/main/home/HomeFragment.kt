package com.dnar.dicodingsubmissionbfaa.ui.main.home

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dnar.dicodingsubmissionbfaa.R
import com.dnar.dicodingsubmissionbfaa.data.adapter.UserSearchAdapter
import com.dnar.dicodingsubmissionbfaa.data.model.Status
import com.dnar.dicodingsubmissionbfaa.data.model.UserSearch
import com.dnar.dicodingsubmissionbfaa.data.util.changeNavigation
import com.dnar.dicodingsubmissionbfaa.data.util.hide
import com.dnar.dicodingsubmissionbfaa.data.util.hideKeyboard
import com.dnar.dicodingsubmissionbfaa.data.util.show
import com.dnar.dicodingsubmissionbfaa.databinding.FragmentHomeBinding
import com.dnar.dicodingsubmissionbfaa.ui.base.BaseFragment

// Home fragment implements dagger fragment
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(),
    UserSearchAdapter.Listener {

    private var rvUserSearchAdapter = UserSearchAdapter(this)

    override var getLayoutId: Int = R.layout.fragment_home
    override var getViewModel: Class<HomeViewModel> = HomeViewModel::class.java
    override var title: MutableLiveData<String> = MutableLiveData("Github User's")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setContent(if (rvUserSearchAdapter.itemCount <= 0) 1 else 3)

        mViewBinding.apply {
            homeRvUser.apply {
                adapter = rvUserSearchAdapter
            }

            homeEtSearch.apply {
                setOnEditorActionListener { _, actionId, _ ->
                    var handled = false

                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        searchUser()
                        handled = true
                    }
                    handled
                }
            }

            homeBtnSearch.setOnClickListener {
                searchUser()
            }
        }
    }

    private fun searchUser() {
        mViewBinding.apply {
            homeRvUser.requestFocus()

            homeProgressBar.show()
            context?.hideKeyboard(requireView())

            observeUserSearch(homeEtSearch.text.toString())
        }
    }

    private fun setContent(condition: Int) {
        mViewBinding.apply {
            isLoaded = condition == 3
            isNotFound = condition == 2
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
                                mViewBinding.homeProgressBar.hide()

                                if (data.total_count > 0) {
                                    setContent(3)
                                    rvUserSearchAdapter.setList(data.items)
                                } else {
                                    setContent(2)
                                }
                            }
                        }
                        Status.StatusType.ERROR -> {
                            mViewBinding.homeProgressBar.hide()

                            setContent(2)
                            Toast.makeText(activity, status.message, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            })
    }

    override fun onUserClickListener(view: View, data: UserSearch) {
        val action = HomeFragmentDirections.actionHomeFragmentToUserDetailFragment(data.login)
        view.changeNavigation(action)
    }
}
