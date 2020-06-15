package com.dnar.dicodingsubmissionbfaa.data.repositories

import com.dnar.dicodingsubmissionbfaa.data.db.entities.UserEntity
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

// Favorite repository; Keyword : Repository
class FavoriteRepository @Inject constructor(
    private val userRepository: UserRepository
) {

    private val compositeDisposable = CompositeDisposable()

    // Function : for check is data stored in database ?
    fun checkFavoriteUser(userId: Int) = userRepository.checkFavoriteUser(userId)

    // Function : for save data into database
    fun addFavoriteUser(user: UserEntity) = userRepository.addFavoriteUser(user)

    // Function : for delete data in database
    fun deleteFavoriteUser(user: UserEntity) = userRepository.deleteFavoriteUser(user)

    // Function : for get all users from database
    fun getUsers() = userRepository.getUsers()

    // Function : for get user by id from database
    fun getUserById(userId: Int) = userRepository.getUserById(userId)

    // Function : for dispose profile repository composite
    fun disposeComposite() {
        compositeDisposable.dispose()
    }
}