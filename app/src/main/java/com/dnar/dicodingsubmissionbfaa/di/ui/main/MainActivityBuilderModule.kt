package com.dnar.dicodingsubmissionbfaa.di.ui.main

import com.dnar.dicodingsubmissionbfaa.ui.main.home.HomeFragment
import com.dnar.dicodingsubmissionbfaa.ui.main.user_detail.UserDetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

// Main Activity Builder Module; Keyword : Dagger2
@Module
abstract class MainActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeUserDetailFragment(): UserDetailFragment
}