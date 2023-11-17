package com.tokodizital.jajanmania.vendor.home.di

import com.tokodizital.jajanmania.vendor.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val vendorHomeModule = module {
    viewModel { HomeViewModel(get(), get()) }
}