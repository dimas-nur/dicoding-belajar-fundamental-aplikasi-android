package com.dnar.dicodingsubmissionbfaa.di.app

import com.dnar.dicodingsubmissionbfaa.data.network.api.ApiHelper
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
    fun provideApiHelper(): ApiHelper =
        ApiHelper.create()
}