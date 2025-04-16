package com.android.fakestoreapp.di

import com.android.fakestoreapp.ui.main.MainActivityVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainActivityVM(get()) }
}