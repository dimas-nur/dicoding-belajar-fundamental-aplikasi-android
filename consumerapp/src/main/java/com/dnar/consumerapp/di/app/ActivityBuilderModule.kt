package com.dnar.consumerapp.di.app

import com.dnar.consumerapp.di.ui.main.MainActivityBuilderModule
import com.dnar.consumerapp.di.ui.main.MainModule
import com.dnar.consumerapp.di.ui.main.MainViewModelModule
import com.dnar.consumerapp.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

// Activity Builder Module; Keyword : Dagger2
@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(
        modules = [
            MainActivityBuilderModule::class,
            MainViewModelModule::class,
            MainModule::class
        ]
    )
    abstract fun contributeMainActivity(): MainActivity

}