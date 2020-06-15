package com.dnar.dicodingsubmissionbfaa.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dnar.dicodingsubmissionbfaa.data.db.AppDatabase
import com.dnar.dicodingsubmissionbfaa.data.db.entities.UserEntity
import com.dnar.dicodingsubmissionbfaa.data.model.Status
import com.dnar.dicodingsubmissionbfaa.data.model.UserDetail
import com.dnar.dicodingsubmissionbfaa.data.model.UserSearch
import com.dnar.dicodingsubmissionbfaa.data.network.api.ApiHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

// Profile Repository; Keyword : Repository
class ProfileRepository @Inject constructor(
    val api: ApiHelper,
    private val db: AppDatabase
) {

    private val compositeDisposable = CompositeDisposable()

    // Function : for get data detail user from api
    fun getDetail(username: String): LiveData<Status<UserDetail>> {
        val liveData = MutableLiveData<Status<UserDetail>>()

        compositeDisposable.add(
            api.getUser(username)
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

    // Function : for get data list followers user from api
    fun getFollowersUser(username: String): LiveData<Status<List<UserSearch>>> {
        val liveData = MutableLiveData<Status<List<UserSearch>>>()

        compositeDisposable.add(
            api.getUserFollowers(username)
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

    // Function : for get data list following user from api
    fun getFollowingUser(username: String): LiveData<Status<List<UserSearch>>> {
        val liveData = MutableLiveData<Status<List<UserSearch>>>()

        compositeDisposable.add(
            api.getUserFollowing(username)
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

    // Function : for dispose profile repository composite
    fun disposeComposite() {
        compositeDisposable.dispose()
    }
}