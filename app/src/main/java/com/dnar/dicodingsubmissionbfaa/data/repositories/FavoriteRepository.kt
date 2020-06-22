package com.dnar.dicodingsubmissionbfaa.data.repositories

import android.content.Context
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

// Favorite repository; Keyword : Repository
class FavoriteRepository @Inject constructor(
    private val userRepository: UserRepository
) {

    private val compositeDisposable = CompositeDisposable()

    // Function : for get all users from database
    fun getUsers(context: Context) = userRepository.getUsers(context)

    // Function : for dispose profile repository composite
    fun disposeComposite() {
        compositeDisposable.dispose()
    }
}