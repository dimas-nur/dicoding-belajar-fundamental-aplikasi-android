package com.dnar.dicodingsubmissionbfaa.ui.main.home

import android.app.Application
import androidx.lifecycle.ViewModel
import com.dnar.dicodingsubmissionbfaa.data.model.User
import com.dnar.dicodingsubmissionbfaa.data.util.getDataFromJsonAsset
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val application: Application) : ViewModel() {

    // Func : Get list User Object from file json
    fun getUserList(): List<User> {
        val jsonFileString = getDataFromJsonAsset(application, "github_user.json")
        val jsonType = object : TypeToken<List<User>>() {}.type

        return Gson().fromJson(jsonFileString, jsonType)
    }
}
