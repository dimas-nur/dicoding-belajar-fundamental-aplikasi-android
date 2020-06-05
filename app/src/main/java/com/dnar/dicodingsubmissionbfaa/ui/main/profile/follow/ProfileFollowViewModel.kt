package com.dnar.dicodingsubmissionbfaa.ui.main.profile.follow

import androidx.lifecycle.ViewModel
import com.dnar.dicodingsubmissionbfaa.data.repositories.ProfileRepository
import javax.inject.Inject

class ProfileFollowViewModel @Inject constructor(
    private val repository: ProfileRepository
) : ViewModel() {

    fun getFollowers(username: String) = repository.getFollowersUser(username)

    fun getFollowing(username: String) = repository.getFollowingUser(username)

    override fun onCleared() {
        repository.disposeComposite()
        super.onCleared()
    }
}
