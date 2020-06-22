package com.dnar.dicodingsubmissionbfaa.di.ui.main.favorite

import com.dnar.dicodingsubmissionbfaa.data.repositories.FavoriteRepository
import com.dnar.dicodingsubmissionbfaa.data.repositories.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

// Main - Favorite Module; Keyword : Dagger2
@Module
object FavoriteModule {

    @Singleton
    @JvmStatic
    @Provides
    fun provideProfileRepository(
        userRepository: UserRepository
    ) = FavoriteRepository(userRepository)
}