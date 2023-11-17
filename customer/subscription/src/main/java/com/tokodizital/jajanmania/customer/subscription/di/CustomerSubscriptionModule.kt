package com.tokodizital.jajanmania.customer.subscription.di

import com.tokodizital.jajanmania.customer.subscription.SubscriptionViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val customerSubscriptionModule = module {
    viewModel { SubscriptionViewModel(get(), get()) }
}