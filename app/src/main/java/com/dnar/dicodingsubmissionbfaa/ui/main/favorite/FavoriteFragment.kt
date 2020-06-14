package com.dnar.dicodingsubmissionbfaa.ui.main.favorite

import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.dnar.dicodingsubmissionbfaa.R
import com.dnar.dicodingsubmissionbfaa.databinding.FragmentFavoriteBinding
import com.dnar.dicodingsubmissionbfaa.ui.base.BaseFragment

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding, FavoriteViewModel>() {

    override var getLayoutId: Int = R.layout.fragment_favorite
    override var getViewModel: Class<FavoriteViewModel> = FavoriteViewModel::class.java
    override var title: MutableLiveData<String> = MutableLiveData("Favorite")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set condition PlaceholderView
        setContentPlaceholder(7)

        // Configure ViewBinding
        mViewBinding.apply {

            // Set button BackToolbar onClickListener
            favoriteBtnBack.setOnClickListener {
                activity?.onBackPressed()
            }
        }
    }
}