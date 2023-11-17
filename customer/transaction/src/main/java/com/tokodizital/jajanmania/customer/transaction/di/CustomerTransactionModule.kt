package com.tokodizital.jajanmania.customer.transaction.di

import com.tokodizital.jajanmania.customer.transaction.history.CustomerTransactionHistoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val customerTransactionModule = module {
    viewModel { CustomerTransactionHistoryViewModel(get(), get()) }
}