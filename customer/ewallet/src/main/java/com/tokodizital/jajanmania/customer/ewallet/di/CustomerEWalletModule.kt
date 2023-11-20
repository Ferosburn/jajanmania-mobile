package com.tokodizital.jajanmania.customer.ewallet.di

import com.tokodizital.jajanmania.customer.ewallet.EWalletCustViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val customerEWalletModule = module {
    viewModel { EWalletCustViewModel(get(), get()) }
}