package com.tokodizital.jajanmania.customer.home.di

import com.tokodizital.jajanmania.customer.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val customerHomeModule = module {
    viewModel { HomeViewModel(get(), get()) }
}