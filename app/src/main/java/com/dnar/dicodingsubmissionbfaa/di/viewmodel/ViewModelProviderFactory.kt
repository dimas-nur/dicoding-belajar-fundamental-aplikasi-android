package com.dimasnur.daggerpractice.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

// View Model Factory for Dependency Injection
class ViewModelProviderFactory @Inject constructor(private val creators: Map<Class<out ViewModel?>?, @JvmSuppressWildcards Provider<ViewModel>?>) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        var creator: Provider<out ViewModel>? = creators[modelClass]
        if (creator == null) {

            for ((key, value) in creators) {
                if (modelClass.isAssignableFrom(key!!)) {
                    creator = value
                    break
                }
            }
        }

        requireNotNull(creator) { "unknown model class $modelClass" }

        return try {
            creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}