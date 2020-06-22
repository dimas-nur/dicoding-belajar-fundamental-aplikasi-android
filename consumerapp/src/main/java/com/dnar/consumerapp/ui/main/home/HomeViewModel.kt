package com.dnar.consumerapp.ui.main.home

import android.content.Context
import androidx.lifecycle.ViewModel
import com.dnar.consumerapp.data.repositories.HomeRepository
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {

    // Function : for get all users from database
    fun getUsers(context: Context) = repository.getUsers(context)

    override fun onCleared() {
        repository.disposeComposite()
        super.onCleared()
    }
}