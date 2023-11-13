package com.tokodizital.jajanmania.vendor.transaction.di

import com.tokodizital.jajanmania.vendor.transaction.history.TransactionHistoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val vendorTransactionModule = module {
    viewModel { TransactionHistoryViewModel(get(), get(), get()) }
}