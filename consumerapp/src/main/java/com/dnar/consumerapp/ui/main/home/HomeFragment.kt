package com.dnar.consumerapp.ui.main.home

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dnar.consumerapp.R
import com.dnar.consumerapp.data.adapter.UserFavoriteAdapter
import com.dnar.consumerapp.data.model.Status
import com.dnar.consumerapp.data.model.UserDetail
import com.dnar.consumerapp.databinding.FragmentHomeBinding
import com.dnar.consumerapp.ui.base.BaseFragment
import com.dnar.consumerapp.util.changeNavigation
import com.dnar.consumerapp.util.hide
import com.dnar.consumerapp.util.show
import com.dnar.consumerapp.util.showDialogWarning

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(),
    UserFavoriteAdapter.Listener {

    private var rvUserFavoriteAdapter = UserFavoriteAdapter(this)

    override var getLayoutId: Int = R.layout.fragment_home
    override var getViewModel: Class<HomeViewModel> = HomeViewModel::class.java
    override var title: MutableLiveData<String> = MutableLiveData("Home")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set condition PlaceholderView
        setContentPlaceholder(if (rvUserFavoriteAdapter.itemCount <= 0) 7 else 1)

        // Configure ViewBinding
        mViewBinding.apply {

            // Inflate options menu & set optionsClickListener
            homeToolbar.apply {
                inflateMenu(R.menu.main_menu)
                setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.action_home_setting -> {
                            startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
                        }
                    }
                    false
                }
            }

            // Set adapter user RecyclerView
            homeRvUser.apply {
                adapter = rvUserFavoriteAdapter
            }
        }

        observeUsersFavorite()
    }

    // Function : for observe data user favorite from database
    private fun observeUsersFavorite() {
        mViewBinding.apply {

            // Show ProgressBar
            homeProgressBar.show()

            context?.let { context ->
                mViewModel.getUsers(context)
                    .observe(viewLifecycleOwner, Observer {
                        it?.let { status ->
                            when (status.status) {
                                Status.StatusType.SUCCESS -> {

                                    // Hide ProgressBar and set list data in RecyclerViewAdapter
                                    it.data?.let { data ->
                                        homeProgressBar.hide()

                                        if (data.isNotEmpty()) {
                                            // When data is not empty
                                            setContentPlaceholder(1)
                                            rvUserFavoriteAdapter.setList(data)
                                        } else {
                                            // When data is empty
                                            setContentPlaceholder(7)
                                        }
                                    }
                                }
                                Status.StatusType.ERROR -> {

                                    // Hide ProgressBar and show warning dialog
                                    homeProgressBar.hide()

                                    showDialogWarning(mDialog, status.message ?: "Error", null)
                                    setContentPlaceholder(2)
                                }
                            }
                        }
                    })
            }
        }
    }

    override fun onUserClickListener(view: View, data: UserDetail) {
        val action = HomeFragmentDirections.actionHomeFragmentToProfileFragment(
            data
        )
        view.changeNavigation(action)
    }

}