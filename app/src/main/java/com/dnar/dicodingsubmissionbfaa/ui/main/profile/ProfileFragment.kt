package com.dnar.dicodingsubmissionbfaa.ui.main.profile

import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import cn.pedant.SweetAlert.SweetAlertDialog
import com.dnar.dicodingsubmissionbfaa.R
import com.dnar.dicodingsubmissionbfaa.data.db.entities.UserEntity
import com.dnar.dicodingsubmissionbfaa.data.model.Status
import com.dnar.dicodingsubmissionbfaa.databinding.FragmentProfileBinding
import com.dnar.dicodingsubmissionbfaa.ui.base.BaseFragment
import com.dnar.dicodingsubmissionbfaa.ui.main.MainActivity
import com.dnar.dicodingsubmissionbfaa.util.hide
import com.dnar.dicodingsubmissionbfaa.util.show
import com.dnar.dicodingsubmissionbfaa.util.showDialogWarning
import com.dnar.dicodingsubmissionbfaa.util.toUserEntity
import com.shashank.sony.fancytoastlib.FancyToast

class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>() {

    private lateinit var mDialog: SweetAlertDialog

    private var userEntity: UserEntity? = null

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

            // Set button FloatingAction onClickListener
            profileFloatingAction.setOnClickListener {
                // Show ProgressBar
                profileProgressBar.show()

                if (userEntity != null) {
                    // Delete from favorite user
                    deleteFavorite()
                } else {
                    // Add to favorite user
                    addFavorite()
                }
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
                                it.data?.let { data ->
                                    setContentPlaceholder(1)

                                    // Observe is favorite user ?
                                    observeCheckFavoriteUser(data.id)
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

    // Function : for set floating action button state
    private fun checkIsFavorite() {
        val drawable =
            if (userEntity != null) resources.getDrawable(
                R.drawable.ic_favorite_favorite_filled,
                null
            ) else resources.getDrawable(R.drawable.ic_favorite_favorite_unfilled, null)

        mViewBinding.apply {
            profileFloatingAction.setImageDrawable(drawable)

            // Hide ProgressBar and show warning dialog
            profileProgressBar.hide()
        }
    }

    // Function : for observe data is favorite user ?
    private fun observeCheckFavoriteUser(userId: Int) {
        mViewBinding.apply {
            mViewModel.checkFavoriteUser(userId)
                .observe(viewLifecycleOwner, Observer {
                    it?.let { userEntity = it }
                    checkIsFavorite()
                })
        }
    }

    // Function : for add user favorite to database
    private fun addFavorite() {
        mViewBinding.user?.toUserEntity()?.let { data ->
            mViewModel.addFavoriteUser(data)
                .subscribe({
                    userEntity = data

                    // Success add to database
                    FancyToast.makeText(
                        context,
                        getString(R.string.favorite_success_add),
                        FancyToast.LENGTH_SHORT,
                        FancyToast.SUCCESS,
                        false
                    ).show()
                    checkIsFavorite()
                }, {
                    userEntity = null

                    // Failed add to database
                    showDialogWarning(mDialog, it.message, null)
                    checkIsFavorite()
                })
        }
    }

    // Function : for delete user favorite to database
    private fun deleteFavorite() {
        mViewBinding.user?.toUserEntity()?.let { data ->
            mViewModel.deleteFavoriteUser(data)
                .subscribe({
                    userEntity = null

                    // Success delete from database
                    FancyToast.makeText(
                        context,
                        getString(R.string.favorite_success_deleted),
                        FancyToast.LENGTH_SHORT,
                        FancyToast.SUCCESS,
                        false
                    ).show()
                    checkIsFavorite()
                }, {
                    userEntity = data

                    // Failed delete from database
                    showDialogWarning(mDialog, it.message, null)
                    checkIsFavorite()
                })
        }
    }
}
