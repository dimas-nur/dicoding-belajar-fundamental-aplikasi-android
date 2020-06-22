package com.dnar.consumerapp.di.viewmodel

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

// View Model Provider Factory Module; Keyword : Dagger2
@Module
abstract class ViewModelProviderFactoryModule {

    @Binds
    abstract fun bindViewModelProvider(viewModelProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}