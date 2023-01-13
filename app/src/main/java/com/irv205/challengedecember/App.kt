package com.irv205.challengedecember

import android.app.Application
import com.irv205.challengedecember.core.di.*
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(networkModule, repositoryModule, dataSourceModule, dispatcherModule, viewModelModule)
        }
    }

}