package com.dnar.dicodingsubmissionbfaa.di.ui.main.profile

import com.dnar.dicodingsubmissionbfaa.data.network.api.ApiHelper
import com.dnar.dicodingsubmissionbfaa.data.repositories.ProfileRepository
import com.dnar.dicodingsubmissionbfaa.data.repositories.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

// Main - Profile Module; Keyword : Dagger2
@Module
object ProfileModule {

    @Singleton
    @JvmStatic
    @Provides
    fun provideProfileRepository(
        api: ApiHelper,
        userRepository: UserRepository
    ) = ProfileRepository(api, userRepository)
}