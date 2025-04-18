package com.android.core.di.repositorymodule

import com.android.core.data.repository.LocalRepositoryImpl
import com.android.core.data.repository.MainRepositoryImpl
import com.android.core.domain.repository.LocalRepository
import com.android.core.domain.repository.MainRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<MainRepository>{
        MainRepositoryImpl(get())
    }

    single<LocalRepository> {
        LocalRepositoryImpl(get())
    }
}