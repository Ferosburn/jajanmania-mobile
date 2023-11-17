package com.tokodizital.jajanmania.vendor.ewallet.di

import com.tokodizital.jajanmania.vendor.ewallet.EWalletViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val vendorEWalletModule = module {
    viewModel { EWalletViewModel(get(), get()) }
}