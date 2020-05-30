package com.dnar.dicodingsubmissionbfaa.di.ui.main

import androidx.lifecycle.ViewModel
import com.dimasnur.daggerpractice.di.viewmodel.ViewModelKey
import com.dnar.dicodingsubmissionbfaa.ui.main.MainViewModel
import dagger.Binds
import dagger.multibindings.IntoMap

// Main View Model Module; Keyword : dagger2
abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel
}