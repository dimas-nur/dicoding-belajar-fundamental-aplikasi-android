package com.dnar.dicodingsubmissionbfaa.ui.main.home

import android.app.Application
import androidx.lifecycle.ViewModel
import com.dnar.dicodingsubmissionbfaa.data.model.User
import com.dnar.dicodingsubmissionbfaa.data.util.getDataFromJsonAsset
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class HomeViewModel @Inject constructor(val application: Application) : ViewModel() {

    fun getUserList(): List<User> {
        val jsonFileString = getDataFromJsonAsset(application, "githubuser.json")
        val jsonType = object : TypeToken<List<User>>() {}.type

        return Gson().fromJson(jsonFileString, jsonType)
    }
}
