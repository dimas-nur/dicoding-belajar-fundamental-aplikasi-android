package com.dnar.dicodingsubmissionbfaa.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dnar.dicodingsubmissionbfaa.data.db.entities.UserEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface UserDao {

    // Function : Insert user into database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(userEntity: UserEntity): Completable

    // Function : Delete user from database
    @Delete
    fun deleteUser(userEntity: UserEntity): Completable

    // Function : Get list user from database
    @Query("SELECT * FROM users_favorite ORDER BY name ASC")
    fun getUsers(): LiveData<List<UserEntity>>

    // Function : Get user by id from database
    @Query("SELECT * FROM users_favorite WHERE id = :userId")
    fun getUserById(userId: Int): Single<UserEntity>
}