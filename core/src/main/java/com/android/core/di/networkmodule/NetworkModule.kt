package com.android.core.di.networkmodule

import com.android.core.di.networkmodule.provideBaseClient
import com.android.core.di.networkmodule.provideBaseGsonFactory
import com.android.core.di.networkmodule.provideBaseInterceptor
import com.android.core.di.networkmodule.provideBaseRetrofit
import com.android.core.di.networkmodule.provideGsonBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val networkModule = module {
    single { provideBaseInterceptor() }
    single { provideBaseGsonFactory() }
    single { provideGsonBuilder() }
    single { provideBaseClient(get(), androidContext()) }
    single {
        provideBaseRetrofit(
            gson = get(),
            gsonBuilder = get(),
            client = get(),
            baseURL = "https://api.spacexdata.com/",
        )
    }
}