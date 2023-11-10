package com.tokodizital.jajanmania.customer.vendor.di

import com.tokodizital.jajanmania.customer.vendor.nearbyvendor.CustomerVendorViewModel
import kotlinx.coroutines.FlowPreview
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@FlowPreview
val customerVendorModule = module {
    viewModel { CustomerVendorViewModel(get(), get()) }
    viewModel { CustomerVendorDetailViewModel(get(), get()) }
}