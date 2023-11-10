package com.tokodizital.jajanmania.core.domain.di

import com.tokodizital.jajanmania.core.domain.interactor.VendorDetailInteractor
import com.tokodizital.jajanmania.core.domain.interactor.CustomerInteractor
import com.tokodizital.jajanmania.core.domain.interactor.CustomerSessionInteractor
import com.tokodizital.jajanmania.core.domain.interactor.VendorInteractor
import com.tokodizital.jajanmania.core.domain.interactor.VendorSessionInteractor
import com.tokodizital.jajanmania.core.domain.usecase.VendorDetailUseCase
import com.tokodizital.jajanmania.core.domain.usecase.CustomerSessionUseCase
import com.tokodizital.jajanmania.core.domain.usecase.CustomerUseCase
import com.tokodizital.jajanmania.core.domain.usecase.VendorSessionUseCase
import com.tokodizital.jajanmania.core.domain.usecase.VendorUseCase
import org.koin.dsl.module

val domainModule = module {
    single<VendorUseCase> { VendorInteractor(get()) }
    single<VendorSessionUseCase> { VendorSessionInteractor(get()) }
    single<VendorDetailUseCase> { VendorDetailInteractor(get()) }
    single<CustomerUseCase> { CustomerInteractor(get()) }
    single<CustomerSessionUseCase> { CustomerSessionInteractor(get()) }
}