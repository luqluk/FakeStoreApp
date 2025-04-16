package com.android.core.di.servicemodule

import com.android.core.data.service.RocketService
import org.koin.dsl.module
import retrofit2.Retrofit

val serviceCoreModule = module {
    fun RocketService(retrofit: Retrofit) = retrofit.create(RocketService::class.java)

    single { RocketService(get()) }
}