package com.dnar.dicodingsubmissionbfaa.ui.main.profile.follow

import androidx.lifecycle.ViewModel
import com.dnar.dicodingsubmissionbfaa.data.repositories.ProfileRepository
import javax.inject.Inject

class ProfileFollowViewModel @Inject constructor(
    private val repository: ProfileRepository
) : ViewModel() {

    // Function : for get data list followers from api
    fun getFollowers(username: String) = repository.getFollowersUser(username)

    // Function : for get data list following from api
    fun getFollowing(username: String) = repository.getFollowingUser(username)

    override fun onCleared() {
        repository.disposeComposite()
        super.onCleared()
    }
}
