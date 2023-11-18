package com.tokodizital.customer.topup.di

import com.tokodizital.customer.topup.CustomerTopUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val customerTopUpModule = module {
    viewModel { CustomerTopUpViewModel(get(), get()) }
}