package com.example.viewmodeltest

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class ViewModelTestApplication: Application() {

    override fun onCreate() {
        super.onCreate()
       // timber
        Timber.plant(Timber.DebugTree())
    }
}