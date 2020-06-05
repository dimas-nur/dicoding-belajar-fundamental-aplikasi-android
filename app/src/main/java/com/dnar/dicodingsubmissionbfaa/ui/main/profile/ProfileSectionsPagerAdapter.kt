package com.dnar.dicodingsubmissionbfaa.ui.main.profile

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dnar.dicodingsubmissionbfaa.ui.main.profile.follow.ProfileFollowFragment

class ProfileSectionsPagerAdapter(
    fm: FragmentManager,
    private val username: String
) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val tabTittles = listOf<String>(
        "Followers",
        "Following"
    )

    override fun getItem(position: Int): Fragment =
        ProfileFollowFragment.newInstance(position, username)

    override fun getPageTitle(position: Int): CharSequence? =
        tabTittles[position]

    override fun getCount(): Int = tabTittles.size
}