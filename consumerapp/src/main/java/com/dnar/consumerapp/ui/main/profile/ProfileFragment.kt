package com.dnar.consumerapp.ui.main.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dnar.consumerapp.R
import com.dnar.consumerapp.data.model.Status
import com.dnar.consumerapp.data.model.UserDetail
import com.dnar.consumerapp.databinding.FragmentProfileBinding
import com.dnar.consumerapp.ui.base.BaseFragment
import com.dnar.consumerapp.util.hide
import com.dnar.consumerapp.util.show
import com.dnar.consumerapp.util.showDialogWarning
import com.shashank.sony.fancytoastlib.FancyToast

class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>() {

    private var userDetail: UserDetail? = null

    override var getLayoutId: Int = R.layout.fragment_profile
    override var getViewModel: Class<ProfileViewModel> = ProfileViewModel::class.java
    override var title: MutableLiveData<String> = MutableLiveData("Profile")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set condition PlaceholderView
        setContentPlaceholder(1)

        // Configure ViewBinding
        mViewBinding.apply {

            // Get data from arguments
            arguments?.let {
                val user = ProfileFragmentArgs.fromBundle(it).user
                val username = ProfileFragmentArgs.fromBundle(it).username

                if (user != null) {
                    setContentPlaceholder(1)

                    // Observe is favorite user ?
                    context?.let { context -> observeCheckFavoriteUser(user.id, context) }
                    this.user = user
                } else {
                    observeDetail(username)
                }

                // Set ViewPagerAdapter
                profileViewPager.adapter =
                    ProfileSectionsPagerAdapter(
                        childFragmentManager,
                        this@ProfileFragment,
                        username,
                        user
                    )
                profileTabLayout.setupWithViewPager(profileViewPager)
            }

            profileToolbar.apply {
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

            // Set button BackToolbar onClickListener
            profileBtnBack.setOnClickListener {
                activity?.onBackPressed()
            }

            // Set button FloatingAction onClickListener
            profileFloatingAction.setOnClickListener {
                if (userDetail != null) {
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
                                    context?.let { context ->
                                        observeCheckFavoriteUser(
                                            data.id,
                                            context
                                        )
                                    }
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
            if (userDetail != null) resources.getDrawable(
                R.drawable.ic_favorite_favorite_filled,
                null
            ) else resources.getDrawable(R.drawable.ic_favorite_favorite_unfilled, null)

        mViewBinding.apply {
            profileFloatingAction.setImageDrawable(drawable)

            // Hide ProgressBar and
            if (profileProgressBar.isShown)
                profileProgressBar.hide()
        }
    }

    // Function : for observe data is favorite user ?
    private fun observeCheckFavoriteUser(userId: Int, context: Context) {
        mViewBinding.apply {
            mViewModel.checkFavoriteUser(userId, context)
                .observe(viewLifecycleOwner, Observer {
                    it?.let { status ->
                        when (status.status) {
                            Status.StatusType.SUCCESS -> {
                                it.data?.let { data ->
                                    userDetail = data
                                }
                            }
                            Status.StatusType.ERROR -> {
                                userDetail = null
                            }
                        }

                        checkIsFavorite()
                    }
                })
        }
    }

    // Function : for add user favorite to database
    private fun addFavorite() {
        mViewBinding.user?.let { data ->
            context?.let { context ->
                mViewModel.addFavoriteUser(data, context)
                    .observe(viewLifecycleOwner, Observer {
                        it?.let {
                            userDetail = data

                            // Success add to database
                            FancyToast.makeText(
                                context,
                                getString(R.string.favorite_success_add),
                                FancyToast.LENGTH_SHORT,
                                FancyToast.SUCCESS,
                                false
                            ).show()
                        }
                        checkIsFavorite()
                    })
            }
        }
    }

    // Function : for delete user favorite to database
    private fun deleteFavorite() {
        mViewBinding.user?.let { data ->
            context?.let { context ->
                mViewModel.deleteFavoriteUser(data, context)
                    .observe(viewLifecycleOwner, Observer {
                        it?.let {
                            userDetail = null

                            // Success delete from database
                            FancyToast.makeText(
                                context,
                                getString(R.string.favorite_success_deleted),
                                FancyToast.LENGTH_SHORT,
                                FancyToast.SUCCESS,
                                false
                            ).show()
                        }
                        checkIsFavorite()
                    })
            }
        }
    }
}
