package com.dnar.consumerapp.di.ui.main

import com.dnar.consumerapp.di.HomeScope
import com.dnar.consumerapp.di.ProfileScope
import com.dnar.consumerapp.di.ui.main.home.HomeModule
import com.dnar.consumerapp.di.ui.main.profile.ProfileModule
import com.dnar.consumerapp.ui.main.home.HomeFragment
import com.dnar.consumerapp.ui.main.profile.ProfileFragment
import com.dnar.consumerapp.ui.main.profile.follow.ProfileFollowFragment
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

    @ProfileScope
    @ContributesAndroidInjector(
        modules = [
            ProfileModule::class
        ]
    )
    abstract fun contributeProfileFollowFragment(): ProfileFollowFragment

}