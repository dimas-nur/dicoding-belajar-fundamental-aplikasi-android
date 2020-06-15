package com.dnar.dicodingsubmissionbfaa.ui.main.favorite

import androidx.lifecycle.ViewModel
import com.dnar.dicodingsubmissionbfaa.data.db.entities.UserEntity
import com.dnar.dicodingsubmissionbfaa.data.repositories.FavoriteRepository
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    private val repository: FavoriteRepository
) : ViewModel() {

    // Function : for check is user stored in database ?
    fun checkFavoriteUser(userId: Int) = repository.checkFavoriteUser(userId)

    // Function : for save user favorite into database
    fun addFavoriteUser(user: UserEntity) = repository.addFavoriteUser(user)

    // Function : for delete user favorite in database
    fun deleteFavoriteUser(user: UserEntity) = repository.deleteFavoriteUser(user)

    // Function : for get all users from database
    fun getUsers() = repository.getUsers()

    // Function : for get user by id from database
    fun getUserById(userId: Int) = repository.getUserById(userId)

    override fun onCleared() {
        repository.disposeComposite()
        super.onCleared()
    }
}