package com.android.core.di.networkmodule

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
            baseURL = "https://fakestoreapi.com/",
        )
    }
}