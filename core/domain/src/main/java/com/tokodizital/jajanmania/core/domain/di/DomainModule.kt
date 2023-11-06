package com.tokodizital.jajanmania.core.domain.di

import com.tokodizital.jajanmania.core.domain.interactor.CustomerInteractor
import com.tokodizital.jajanmania.core.domain.interactor.VendorInteractor
import com.tokodizital.jajanmania.core.domain.usecase.CustomerUseCase
import com.tokodizital.jajanmania.core.domain.usecase.VendorUseCase
import org.koin.dsl.module

val domainModule = module {
    single<VendorUseCase> { VendorInteractor(get()) }
    single<CustomerUseCase> { CustomerInteractor(get()) }
}