package com.dnar.dicodingsubmissionbfaa.ui.main.favorite

import android.content.Context
import androidx.lifecycle.ViewModel
import com.dnar.dicodingsubmissionbfaa.data.repositories.FavoriteRepository
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    private val repository: FavoriteRepository
) : ViewModel() {

    // Function : for get all users from database
    fun getUsers(context: Context) = repository.getUsers(context)

    override fun onCleared() {
        repository.disposeComposite()
        super.onCleared()
    }
}