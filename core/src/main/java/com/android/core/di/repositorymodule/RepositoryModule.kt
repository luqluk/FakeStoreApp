package com.android.core.di.repositorymodule

import com.android.core.data.repository.RocketRepositoryImpl
import com.android.core.domain.repository.RocketRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<RocketRepository> {
        RocketRepositoryImpl(get())
    }
}