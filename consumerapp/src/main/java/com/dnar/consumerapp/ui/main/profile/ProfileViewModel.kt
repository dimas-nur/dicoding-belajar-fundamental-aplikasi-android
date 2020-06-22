package com.dnar.consumerapp.ui.main.profile

import android.content.Context
import androidx.lifecycle.ViewModel
import com.dnar.consumerapp.data.model.UserDetail
import com.dnar.consumerapp.data.repositories.ProfileRepository
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository
) : ViewModel() {

    // Function : for get data user detail from api
    fun getDetail(username: String) = repository.getDetail(username)

    // Function : for check is user stored in database ?
    fun checkFavoriteUser(userId: Int, context: Context) =
        repository.checkFavoriteUser(userId, context)

    // Function : for save user favorite into database
    fun addFavoriteUser(user: UserDetail, context: Context) =
        repository.addFavoriteUser(user, context)

    // Function : for delete user favorite in database
    fun deleteFavoriteUser(user: UserDetail, context: Context) =
        repository.deleteFavoriteUser(user, context)

    override fun onCleared() {
        repository.disposeComposite()
        super.onCleared()
    }
}
