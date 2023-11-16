package com.tokodizital.jajanmania.vendor.account.di

import com.tokodizital.jajanmania.vendor.account.profil.AccountViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val vendorAccountModule = module {
    viewModel { AccountViewModel(get(), get()) }
}