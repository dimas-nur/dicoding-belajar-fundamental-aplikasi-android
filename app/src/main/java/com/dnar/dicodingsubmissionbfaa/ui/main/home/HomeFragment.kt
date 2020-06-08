package com.dnar.dicodingsubmissionbfaa.ui.main.home

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import cn.pedant.SweetAlert.SweetAlertDialog
import com.dnar.dicodingsubmissionbfaa.R
import com.dnar.dicodingsubmissionbfaa.data.adapter.UserSearchAdapter
import com.dnar.dicodingsubmissionbfaa.data.model.Status
import com.dnar.dicodingsubmissionbfaa.data.model.UserSearch
import com.dnar.dicodingsubmissionbfaa.data.util.*
import com.dnar.dicodingsubmissionbfaa.databinding.FragmentHomeBinding
import com.dnar.dicodingsubmissionbfaa.ui.base.BaseFragment
import com.dnar.dicodingsubmissionbfaa.ui.main.MainActivity

// Home fragment implements dagger fragment
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(),
    UserSearchAdapter.Listener {

    private lateinit var mDialog: SweetAlertDialog

    private var rvUserSearchAdapter = UserSearchAdapter(this)

    override var getLayoutId: Int = R.layout.fragment_home
    override var getViewModel: Class<HomeViewModel> = HomeViewModel::class.java
    override var title: MutableLiveData<String> = MutableLiveData("Github User's")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setContent(if (rvUserSearchAdapter.itemCount <= 0) 3 else 1)

        mDialog = (activity as MainActivity).mDialog

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
                                    setContent(1)
                                    rvUserSearchAdapter.setList(data.items)
                                } else {
                                    setContent(4)
                                }
                            }
                        }
                        Status.StatusType.ERROR -> {
                            mViewBinding.homeProgressBar.hide()

                            showDialogWarning(mDialog, status.message ?: "Error", null)
                            setContent(2)
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
