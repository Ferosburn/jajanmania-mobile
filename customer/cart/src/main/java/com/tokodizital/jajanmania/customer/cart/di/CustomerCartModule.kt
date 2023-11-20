package com.tokodizital.jajanmania.customer.cart.di

import com.tokodizital.jajanmania.customer.cart.home.CheckoutViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val customerCartModule = module {
    viewModel { CheckoutViewModel(get(), get(), get()) }
}