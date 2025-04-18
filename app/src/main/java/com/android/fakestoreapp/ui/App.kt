package com.android.fakestoreapp.ui

import android.app.Application
import com.android.core.di.databasemodule.product.databaseModule
import com.android.core.di.dispatchermodule.dispatcherModule
import com.android.core.di.networkmodule.networkModule
import com.android.core.di.repositorymodule.repositoryModule
import com.android.core.di.servicemodule.serviceCoreModule
import com.android.fakestoreapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@App)
            modules(
                listOf(
                    databaseModule,
                    dispatcherModule,
                    networkModule,
                    serviceCoreModule,
                    repositoryModule,
                    viewModelModule,

                )
            )
        }
    }
}