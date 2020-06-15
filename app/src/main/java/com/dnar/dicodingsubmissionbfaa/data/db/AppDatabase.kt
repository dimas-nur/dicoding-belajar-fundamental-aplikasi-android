package com.dnar.dicodingsubmissionbfaa.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dnar.dicodingsubmissionbfaa.data.db.dao.UserDao
import com.dnar.dicodingsubmissionbfaa.data.db.entities.UserEntity

// Database class; Keyword : Room SQLite
@Database(
    version = 1,
    entities = [UserEntity::class],
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDao

}