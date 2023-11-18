package com.tokodizital.jajanmania.customer.auth.di

import com.tokodizital.jajanmania.customer.auth.login.LoginCustViewModel
import com.tokodizital.jajanmania.customer.auth.register.RegisterCustViewModel
import com.tokodizital.jajanmania.customer.auth.splashscreen.SplashScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val customerAuthModule = module {
    viewModel { LoginCustViewModel(get(), get()) }
    viewModel { RegisterCustViewModel(get()) }
    viewModel { SplashScreenViewModel(get(), get(), get(), get()) }
}