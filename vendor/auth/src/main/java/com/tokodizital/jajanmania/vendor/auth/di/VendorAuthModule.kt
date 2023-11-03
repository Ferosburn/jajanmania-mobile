package com.tokodizital.jajanmania.vendor.auth.di

import com.tokodizital.jajanmania.vendor.auth.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val vendorAuthModule = module {
    viewModel { LoginViewModel(get()) }
}