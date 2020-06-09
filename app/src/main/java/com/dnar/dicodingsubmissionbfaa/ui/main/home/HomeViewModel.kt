package com.dnar.dicodingsubmissionbfaa.ui.main.home

import androidx.lifecycle.ViewModel
import com.dnar.dicodingsubmissionbfaa.data.repositories.HomeRepository
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {

    // Function : for get data user search from API
    fun getUserSearch(keyword: String) = repository.getUserSearch(keyword)

    override fun onCleared() {
        repository.disposeComposite()
        super.onCleared()
    }
}
