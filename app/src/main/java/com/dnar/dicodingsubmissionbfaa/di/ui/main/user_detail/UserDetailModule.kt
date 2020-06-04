package com.dnar.dicodingsubmissionbfaa.di.ui.main.user_detail

import com.dnar.dicodingsubmissionbfaa.data.network.api.ApiHelper
import com.dnar.dicodingsubmissionbfaa.data.repositories.UserDetailRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

// Main - Home Module; Keyword : Dagger2
@Module
object UserDetailModule {

    @Singleton
    @JvmStatic
    @Provides
    fun provideUserDetailRepository(
        api: ApiHelper
    ) = UserDetailRepository(api)
}