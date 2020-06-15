package com.dnar.dicodingsubmissionbfaa.ui.main.favorite

import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import cn.pedant.SweetAlert.SweetAlertDialog
import com.dnar.dicodingsubmissionbfaa.R
import com.dnar.dicodingsubmissionbfaa.data.adapter.UserFavoriteAdapter
import com.dnar.dicodingsubmissionbfaa.data.db.entities.UserEntity
import com.dnar.dicodingsubmissionbfaa.data.model.Status
import com.dnar.dicodingsubmissionbfaa.databinding.FragmentFavoriteBinding
import com.dnar.dicodingsubmissionbfaa.ui.base.BaseFragment
import com.dnar.dicodingsubmissionbfaa.ui.main.MainActivity
import com.dnar.dicodingsubmissionbfaa.util.*

// Favorite fragment implements dagger fragment
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding, FavoriteViewModel>(),
    UserFavoriteAdapter.Listener {

    private lateinit var mDialog: SweetAlertDialog

    private var rvUserFavoriteAdapter = UserFavoriteAdapter(this)

    override var getLayoutId: Int = R.layout.fragment_favorite
    override var getViewModel: Class<FavoriteViewModel> = FavoriteViewModel::class.java
    override var title: MutableLiveData<String> = MutableLiveData("Favorite")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set condition PlaceholderView
        setContentPlaceholder(if (rvUserFavoriteAdapter.itemCount <= 0) 7 else 1)

        // Set mDialog to get dialog from MainActivity
        mDialog = (activity as MainActivity).mDialog

        // Configure ViewBinding
        mViewBinding.apply {

            // Set button BackToolbar onClickListener
            favoriteBtnBack.setOnClickListener {
                activity?.onBackPressed()
            }

            // Set adapter user RecyclerView
            favoriteRvUser.apply {
                adapter = rvUserFavoriteAdapter
            }
        }

        observeUsersFavorite()
    }

    // Function : for observe data user favorite from database
    private fun observeUsersFavorite() {
        mViewBinding.apply {

            // Show ProgressBar
            favoriteProgressBar.show()

            mViewModel.getUsers()
                .observe(viewLifecycleOwner, Observer {
                    it?.let { status ->
                        when (status.status) {
                            Status.StatusType.SUCCESS -> {

                                // Hide ProgressBar and set list data in RecyclerViewAdapter
                                it.data?.let { data ->
                                    favoriteProgressBar.hide()

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
                                favoriteProgressBar.hide()

                                showDialogWarning(mDialog, status.message ?: "Error", null)
                                setContentPlaceholder(2)
                            }
                        }
                    }
                })
        }
    }

    override fun onUserClickListener(view: View, data: UserEntity) {
        val action = FavoriteFragmentDirections.actionFavoriteFragmentToUserDetailFragment(
            data.login, data.toUserDetail()
        )
        view.changeNavigation(action)
    }
}