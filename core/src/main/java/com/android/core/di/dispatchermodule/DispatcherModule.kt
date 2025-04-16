package com.android.core.di.dispatchermodule

import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val dispatcherModule = module {
    single {
        Dispatchers.IO
    }
}