package com.vdelgado.ml

import android.app.Application
import timber.log.Timber

class MLApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initializeTimber()
    }

    private fun initializeTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}