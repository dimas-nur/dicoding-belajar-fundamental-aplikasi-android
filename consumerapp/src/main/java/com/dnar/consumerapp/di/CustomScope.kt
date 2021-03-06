package com.dnar.consumerapp.di

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