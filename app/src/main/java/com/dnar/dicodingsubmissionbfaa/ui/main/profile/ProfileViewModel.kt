package com.dnar.dicodingsubmissionbfaa.ui.main.profile

import androidx.lifecycle.ViewModel
import com.dnar.dicodingsubmissionbfaa.data.repositories.ProfileRepository
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository
) : ViewModel() {

    fun getDetail(username: String) = repository.getDetail(username)

    override fun onCleared() {
        repository.disposeComposite()
        super.onCleared()
    }
}
