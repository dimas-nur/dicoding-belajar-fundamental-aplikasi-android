package com.dnar.dicodingsubmissionbfaa.di.app

import com.dnar.dicodingsubmissionbfaa.ui.main.MainActivity
import com.dnar.dicodingsubmissionbfaa.di.ui.main.MainViewModelModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

// Activity Builder Module; Keyword : Dagger2
@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(
        modules = [
            MainViewModelModule::class
        ]
    )
    abstract fun contributeMainActivity(): MainActivity

}