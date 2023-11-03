package com.tokodizital.jajanmania.core.data.di

import com.tokodizital.jajanmania.core.data.VendorRepositoryImpl
import com.tokodizital.jajanmania.core.data.vendor.remote.VendorJajanManiaRemoteDataSource
import com.tokodizital.jajanmania.core.data.vendor.remote.service.VendorJajanManiaService
import com.tokodizital.jajanmania.core.domain.repository.VendorRepository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

val dataModule = module {
    single<VendorJajanManiaService> {
        Retrofit.Builder()
            .baseUrl("http://103.167.151.23:3000/api/v1/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }
    single { VendorJajanManiaRemoteDataSource(get()) }
    single<VendorRepository> { VendorRepositoryImpl(get()) }
}
