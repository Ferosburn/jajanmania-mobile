package com.tokodizital.jajanmania.vendor.shop.di

import com.tokodizital.jajanmania.vendor.shop.home.ShopViewModel
import com.tokodizital.jajanmania.vendor.shop.add.FormAddProductViewModel
import com.tokodizital.jajanmania.vendor.shop.manage.ManageShopViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val vendorShopModule = module {
    viewModel { ManageShopViewModel(get(), get()) }
    viewModel { ShopViewModel(get(), get(), get()) }
    viewModel { FormAddProductViewModel(get(), get()) }
}