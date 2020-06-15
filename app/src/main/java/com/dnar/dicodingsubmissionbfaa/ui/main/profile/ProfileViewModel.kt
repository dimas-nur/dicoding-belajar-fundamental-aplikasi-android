package com.dnar.dicodingsubmissionbfaa.ui.main.profile

import androidx.lifecycle.ViewModel
import com.dnar.dicodingsubmissionbfaa.data.db.entities.UserEntity
import com.dnar.dicodingsubmissionbfaa.data.repositories.ProfileRepository
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository
) : ViewModel() {

    // Function : for get data user detail from api
    fun getDetail(username: String) = repository.getDetail(username)

    // Function : for check is user stored in database ?
    fun checkFavoriteUser(userId: Int) = repository.checkFavoriteUser(userId)

    // Function : for save user favorite into database
    fun addFavoriteUser(user: UserEntity) = repository.addFavoriteUser(user)

    // Function : for delete user favorite in database
    fun deleteFavoriteUser(user: UserEntity) = repository.deleteFavoriteUser(user)

    override fun onCleared() {
        repository.disposeComposite()
        super.onCleared()
    }
}
