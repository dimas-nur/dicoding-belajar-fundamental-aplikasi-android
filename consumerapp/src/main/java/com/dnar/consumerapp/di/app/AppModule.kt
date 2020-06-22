package com.dnar.consumerapp.di.app

import com.dnar.consumerapp.data.network.api.ApiHelper
import com.dnar.consumerapp.data.repositories.UserRepository
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
    fun provideUserRepository() = UserRepository()

}