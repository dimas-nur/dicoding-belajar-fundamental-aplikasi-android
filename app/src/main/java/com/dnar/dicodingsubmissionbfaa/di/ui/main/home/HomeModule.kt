package com.dnar.dicodingsubmissionbfaa.di.ui.main.home

import com.dnar.dicodingsubmissionbfaa.data.network.api.ApiHelper
import com.dnar.dicodingsubmissionbfaa.data.repositories.HomeRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

// Main - Home Module; Keyword : Dagger2
@Module
object HomeModule {

    @Singleton
    @JvmStatic
    @Provides
    fun provideHomeRepository(
        api: ApiHelper
    ) = HomeRepository(api)
}