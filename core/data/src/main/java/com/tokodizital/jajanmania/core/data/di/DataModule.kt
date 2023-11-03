package com.tokodizital.jajanmania.core.data.di

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.tokodizital.jajanmania.core.data.VendorRepositoryImpl
import com.tokodizital.jajanmania.core.data.vendor.remote.VendorJajanManiaRemoteDataSource
import com.tokodizital.jajanmania.core.data.vendor.remote.service.VendorJajanManiaService
import com.tokodizital.jajanmania.core.domain.repository.VendorRepository
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

val dataModule = module {

    single(named("chucker")) {
        OkHttpClient.Builder()
            .addInterceptor(ChuckerInterceptor(get()))
            .build()
    }

    single<VendorJajanManiaService> {
        Retrofit.Builder()
            .baseUrl("http://103.167.151.23:3000/api/v1/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(get(named("chucker")))
            .build()
            .create()
    }
    single { VendorJajanManiaRemoteDataSource(get()) }
    single<VendorRepository> { VendorRepositoryImpl(get()) }
}
