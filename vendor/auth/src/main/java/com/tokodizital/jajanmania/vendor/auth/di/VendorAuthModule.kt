package com.tokodizital.jajanmania.vendor.auth.di

import com.tokodizital.jajanmania.vendor.auth.login.LoginViewModel
import com.tokodizital.jajanmania.vendor.auth.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val vendorAuthModule = module {
    viewModel { LoginViewModel(get(), get()) }
    viewModel { RegisterViewModel(get()) }
}