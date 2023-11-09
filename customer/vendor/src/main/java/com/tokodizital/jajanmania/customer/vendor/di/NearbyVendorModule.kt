package com.tokodizital.jajanmania.customer.vendor.di

import com.tokodizital.jajanmania.customer.vendor.CustomerVendorViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val nearbyVendorModule = module {
    viewModel { CustomerVendorViewModel(get()) }
}