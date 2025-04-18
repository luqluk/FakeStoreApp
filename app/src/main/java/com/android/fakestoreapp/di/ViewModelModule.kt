package com.android.fakestoreapp.di

import com.android.fakestoreapp.ui.cart.CartActivityVM
import com.android.fakestoreapp.ui.detail.DetailProductActivityVM
import com.android.fakestoreapp.ui.login.LoginActivityVM
import com.android.fakestoreapp.ui.main.MainActivityVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainActivityVM(get()) }
    viewModel { DetailProductActivityVM(get()) }
    viewModel { CartActivityVM(get()) }
    viewModel { LoginActivityVM(get(), get()) }
}