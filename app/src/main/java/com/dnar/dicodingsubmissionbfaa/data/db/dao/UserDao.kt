package com.dnar.dicodingsubmissionbfaa.data.db.dao

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dnar.dicodingsubmissionbfaa.data.db.entities.UserEntity

// Database Object for User; Keyword : Room
@Dao
interface UserDao {

    // Function : Insert user into database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(userEntity: UserEntity): Long

    // Function : Delete user by id from database
    @Query("DELETE FROM users_favorite WHERE id = :userId")
    fun deleteUser(userId: Int): Int

    // Function : Get list user from database
    @Query("SELECT * FROM users_favorite ORDER BY name ASC")
    fun getUsers(): Cursor

    // Function : Get user by id from database
    @Query("SELECT * FROM users_favorite WHERE id = :userId")
    fun getUserById(userId: Int): Cursor
}