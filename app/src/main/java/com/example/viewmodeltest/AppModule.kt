package com.example.viewmodeltest

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideSavedStateHandle(): SavedStateHandle {
        return SavedStateHandle()
    }

    @Provides
    fun provideViewModelFactory(factory: BaseViewModelFactory): ViewModelProvider.Factory {
        return factory
    }

}