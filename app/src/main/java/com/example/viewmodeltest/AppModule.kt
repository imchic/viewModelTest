package com.example.viewmodeltest

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import javax.inject.Provider


@Module
@InstallIn(ActivityRetainedComponent::class)
object AppModule {

    @Provides
    fun provideSavedStateHandle(): SavedStateHandle {
        return SavedStateHandle()
    }

    @Provides
    fun provideApiService(): ApiService {
        return ApiService.create()
    }

}