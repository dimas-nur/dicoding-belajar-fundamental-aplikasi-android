package com.dnar.consumerapp.data.repositories

import android.content.Context
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dnar.consumerapp.data.model.Status
import com.dnar.consumerapp.data.model.UserDetail
import com.dnar.consumerapp.util.USER_CONTENT_URI
import com.dnar.consumerapp.util.toContentValues
import com.dnar.consumerapp.util.toListUserDetail
import com.dnar.consumerapp.util.toUserDetail
import javax.inject.Inject

// User repository; Keyword : Repository
class UserRepository @Inject constructor() {

    // Function : for check is data stored in database ?
    fun checkFavoriteUser(userId: Int, context: Context): LiveData<Status<UserDetail>> {
        val liveData = MutableLiveData<Status<UserDetail>>()

        val uri = "$USER_CONTENT_URI/$userId".toUri()
        val cursor = context.contentResolver
            .query(uri, null, null, null, null)

        liveData.postValue(null)

        cursor?.let {
            if (it.moveToFirst()) {
                liveData.postValue(Status.success(it.toUserDetail()))
            } else {
                liveData.postValue(Status.error("Error", null))
            }
        }

        cursor?.close()

        return liveData
    }

    // Function : for save data into database
    fun addFavoriteUser(user: UserDetail, context: Context): LiveData<Long> {
        val liveData = MutableLiveData<Long>()

        val cursor =
            context.contentResolver.insert(USER_CONTENT_URI.toUri(), user.toContentValues())

        if (cursor != null) {
            liveData.postValue(1)
        } else {
            liveData.postValue(null)
        }

        return liveData
    }

    // Function : for delete data in database
    fun deleteFavoriteUser(user: UserDetail, context: Context): LiveData<Long> {
        val liveData = MutableLiveData<Long>()

        val uri = "$USER_CONTENT_URI/${user.id}".toUri()
        val cursor =
            context.contentResolver.delete(uri, null, null)

        liveData.postValue(cursor.toLong())

        return liveData
    }

    // Function : for get all users from database
    fun getUsers(context: Context): LiveData<Status<List<UserDetail>>> {
        val liveData = MutableLiveData<Status<List<UserDetail>>>()

        val cursor = context.contentResolver
            .query(USER_CONTENT_URI.toUri(), null, null, null, null)

        if (cursor != null) {
            liveData.postValue(Status.success(cursor.toListUserDetail()))
            cursor.close()
        } else {
            liveData.postValue(Status.error("Error", null))
        }

        return liveData
    }
}