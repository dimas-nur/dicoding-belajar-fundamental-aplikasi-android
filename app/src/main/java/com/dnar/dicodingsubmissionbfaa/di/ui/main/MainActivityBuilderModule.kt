package com.dnar.dicodingsubmissionbfaa.di.ui.main

import com.dnar.dicodingsubmissionbfaa.di.HomeScope
import com.dnar.dicodingsubmissionbfaa.di.UserDetailScope
import com.dnar.dicodingsubmissionbfaa.di.ui.main.home.HomeModule
import com.dnar.dicodingsubmissionbfaa.di.ui.main.user_detail.UserDetailModule
import com.dnar.dicodingsubmissionbfaa.ui.main.home.HomeFragment
import com.dnar.dicodingsubmissionbfaa.ui.main.user_detail.UserDetailFragment
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

    @UserDetailScope
    @ContributesAndroidInjector(
        modules = [
            UserDetailModule::class
        ]
    )
    abstract fun contributeUserDeatilFragment(): UserDetailFragment
}