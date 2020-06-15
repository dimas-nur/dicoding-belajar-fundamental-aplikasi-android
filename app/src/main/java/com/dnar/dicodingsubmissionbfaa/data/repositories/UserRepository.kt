package com.dnar.dicodingsubmissionbfaa.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dnar.dicodingsubmissionbfaa.data.db.AppDatabase
import com.dnar.dicodingsubmissionbfaa.data.db.entities.UserEntity
import com.dnar.dicodingsubmissionbfaa.data.model.Status
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

// User repository; Keyword : Repository
class UserRepository @Inject constructor(
    private val db: AppDatabase
) {

    private val compositeDisposable = CompositeDisposable()

    // Function : for check is data stored in database ?
    fun checkFavoriteUser(userId: Int): LiveData<UserEntity> {
        val liveData = MutableLiveData<UserEntity>()

        compositeDisposable.add(
            db.getUserDao().getUserById(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        it?.let {
                            liveData.postValue(it)
                            return@subscribeBy
                        }
                        liveData.postValue(null)
                    },
                    onError = {
                        liveData.postValue(null)
                    }
                )
        )

        return liveData
    }

    // Function : for save data into database
    fun addFavoriteUser(user: UserEntity) = db.getUserDao().insertUser(user)

    // Function : for delete data in database
    fun deleteFavoriteUser(user: UserEntity) = db.getUserDao().deleteUser(user)

    // Function : for get all users from database
    fun getUsers(): LiveData<Status<List<UserEntity>>> {
        val liveData = MutableLiveData<Status<List<UserEntity>>>()

        compositeDisposable.add(
            db.getUserDao().getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = {
                        it?.let {
                            liveData.postValue(Status.success(it))
                            return@subscribeBy
                        }
                        liveData.postValue(Status.error("Error", null))
                    },
                    onError = {
                        liveData.postValue(Status.error("Error : ${it.message}", null))
                    }
                )
        )

        return liveData
    }

    // Function : for get user by id from database
    fun getUserById(userId: Int) = db.getUserDao().getUserById(userId)

    // Function : for dispose profile repository composite
    fun disposeComposite() {
        compositeDisposable.dispose()
    }
}