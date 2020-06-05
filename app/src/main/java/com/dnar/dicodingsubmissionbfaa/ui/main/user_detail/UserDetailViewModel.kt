package com.dnar.dicodingsubmissionbfaa.ui.main.user_detail

import androidx.lifecycle.ViewModel
import com.dnar.dicodingsubmissionbfaa.data.repositories.UserDetailRepository
import javax.inject.Inject

class UserDetailViewModel @Inject constructor(
    private val repository: UserDetailRepository
) : ViewModel() {

    fun getDetail(username: String) = repository.getDetail(username)

    override fun onCleared() {
        repository.disposeComposite()
        super.onCleared()
    }
}
