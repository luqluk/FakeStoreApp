package com.android.core.di.servicemodule

import com.android.core.data.service.FakeStoreService
import org.koin.dsl.module
import retrofit2.Retrofit

val serviceCoreModule = module {
    fun fakeStoreService(retrofit: Retrofit) = retrofit.create(FakeStoreService::class.java)
    single { fakeStoreService(get()) }
}