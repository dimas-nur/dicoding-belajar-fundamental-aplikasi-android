package com.dnar.dicodingsubmissionbfaa.ui.main.profile

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dnar.dicodingsubmissionbfaa.R
import com.dnar.dicodingsubmissionbfaa.data.db.entities.UserEntity
import com.dnar.dicodingsubmissionbfaa.data.model.Status
import com.dnar.dicodingsubmissionbfaa.databinding.FragmentProfileBinding
import com.dnar.dicodingsubmissionbfaa.ui.base.BaseFragment
import com.dnar.dicodingsubmissionbfaa.util.*
import com.shashank.sony.fancytoastlib.FancyToast

// Profile fragment implements dagger fragment
class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>() {

    private var userEntity: UserEntity? = null

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
                menu.removeItem(R.id.action_home_favorite)
                setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.action_home_setting -> {
                            val action =
                                ProfileFragmentDirections.actionUserDetailFragmentToSettingFragment()
                            view.changeNavigation(action)
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
            if (userEntity != null) resources.getDrawable(
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
                                    userEntity = data
                                }
                            }
                            Status.StatusType.ERROR -> {
                                userEntity = null
                            }
                        }

                        checkIsFavorite()
                    }
                })
        }
    }

    // Function : for add user favorite to database
    private fun addFavorite() {
        mViewBinding.user?.toUserEntity()?.let { data ->
            context?.let { context ->
                mViewModel.addFavoriteUser(data, context)
                    .observe(viewLifecycleOwner, Observer {
                        it?.let {
                            userEntity = data

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
        mViewBinding.user?.toUserEntity()?.let { data ->
            context?.let { context ->
                mViewModel.deleteFavoriteUser(data, context)
                    .observe(viewLifecycleOwner, Observer {
                        it?.let {
                            userEntity = null

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
