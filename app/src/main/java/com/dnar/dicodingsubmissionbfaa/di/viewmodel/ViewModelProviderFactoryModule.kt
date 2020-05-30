package com.dimasnur.daggerpractice.di.viewmodel

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelProviderFactoryModule {

    @Binds
    abstract fun bindViewModelProvider(viewModelProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}