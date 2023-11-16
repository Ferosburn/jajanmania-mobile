package com.tokodizital.jajanmania.customer.profile.di

import com.tokodizital.jajanmania.customer.profile.manage.ManageProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val customerProfileModule = module {
    viewModel { ManageProfileViewModel(get(), get()) }
}