package com.dnar.dicodingsubmissionbfaa.di.ui.main

import com.dnar.dicodingsubmissionbfaa.di.HomeScope
import com.dnar.dicodingsubmissionbfaa.di.ProfileScope
import com.dnar.dicodingsubmissionbfaa.di.ui.main.home.HomeModule
import com.dnar.dicodingsubmissionbfaa.di.ui.main.profile.ProfileModule
import com.dnar.dicodingsubmissionbfaa.ui.main.home.HomeFragment
import com.dnar.dicodingsubmissionbfaa.ui.main.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

// Main Activity Builder Module; Keyword : Dagger2
@Module
abstract class MainActivityBuilderModule {

    @HomeScope
    @ContributesAndroidInjector(
        modules = [
            HomeModule::class
        ]
    )
    abstract fun contributeHomeFragment(): HomeFragment

    @ProfileScope
    @ContributesAndroidInjector(
        modules = [
            ProfileModule::class
        ]
    )
    abstract fun contributeProfileFragment(): ProfileFragment
}