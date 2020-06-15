package com.dnar.dicodingsubmissionbfaa.di

import javax.inject.Scope

// Custom Scope; Keyword : Dagger2
@Scope
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class HomeScope

@Scope
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class ProfileScope

@Scope
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class FavoriteScope

@Scope
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class SettingScope