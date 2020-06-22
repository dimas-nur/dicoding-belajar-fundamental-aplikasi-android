package com.dnar.dicodingsubmissionbfaa.di.ui.main

import com.dnar.dicodingsubmissionbfaa.di.FavoriteScope
import com.dnar.dicodingsubmissionbfaa.di.HomeScope
import com.dnar.dicodingsubmissionbfaa.di.ProfileScope
import com.dnar.dicodingsubmissionbfaa.di.SettingScope
import com.dnar.dicodingsubmissionbfaa.di.ui.main.favorite.FavoriteModule
import com.dnar.dicodingsubmissionbfaa.di.ui.main.home.HomeModule
import com.dnar.dicodingsubmissionbfaa.di.ui.main.profile.ProfileModule
import com.dnar.dicodingsubmissionbfaa.di.ui.main.setting.SettingModule
import com.dnar.dicodingsubmissionbfaa.ui.main.favorite.FavoriteFragment
import com.dnar.dicodingsubmissionbfaa.ui.main.home.HomeFragment
import com.dnar.dicodingsubmissionbfaa.ui.main.profile.ProfileFragment
import com.dnar.dicodingsubmissionbfaa.ui.main.profile.follow.ProfileFollowFragment
import com.dnar.dicodingsubmissionbfaa.ui.main.setting.SettingFragment
import com.dnar.dicodingsubmissionbfaa.ui.main.splash.SplashFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

// Main Activity Builder Module; Keyword : Dagger2
@Module
abstract class MainActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeSplashFragment(): SplashFragment

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

    @FavoriteScope
    @ContributesAndroidInjector(
        modules = [
            FavoriteModule::class
        ]
    )
    abstract fun contributeFavoriteFragment(): FavoriteFragment

    @SettingScope
    @ContributesAndroidInjector(
        modules = [
            SettingModule::class
        ]
    )
    abstract fun contributeSettingFragment(): SettingFragment
}