package com.dnar.dicodingsubmissionbfaa.di.app

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.dnar.dicodingsubmissionbfaa.data.db.AppDatabase
import com.dnar.dicodingsubmissionbfaa.data.network.api.ApiHelper
import com.dnar.dicodingsubmissionbfaa.data.repositories.UserRepository
import com.dnar.dicodingsubmissionbfaa.util.DATABASE_NAME
import com.dnar.dicodingsubmissionbfaa.util.SP_NAME
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

// App Module; Keyword : Dagger2
@Module
object AppModule {

    /* --- API --- */
    @Singleton
    @JvmStatic
    @Provides
    fun provideApiHelper(): ApiHelper = ApiHelper.create()

    /* --- Room --- */
    @Singleton
    @JvmStatic
    @Provides
    fun provideAppDatabase(application: Application) = Room.databaseBuilder(
        application.applicationContext,
        AppDatabase::class.java,
        DATABASE_NAME
    ).allowMainThreadQueries().build()

    @Singleton
    @JvmStatic
    @Provides
    fun provideUserRepository() = UserRepository()

    @Singleton
    @JvmStatic
    @Provides
    fun provideSharedPreferences(application: Application): SharedPreferences =
        application.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
}