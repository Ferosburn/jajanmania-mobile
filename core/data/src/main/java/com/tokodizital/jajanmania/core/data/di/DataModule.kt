package com.tokodizital.jajanmania.core.data.di

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import com.tokodizital.jajanmania.core.data.VendorRepositoryImpl
import com.tokodizital.jajanmania.core.data.VendorSessionRepositoryImpl
import com.tokodizital.jajanmania.core.data.customer.NearbyVendorRepositoryImpl
import com.tokodizital.jajanmania.core.data.customer.remote.NearbyVendorRemoteDataSource
import com.tokodizital.jajanmania.core.data.customer.remote.service.NearbyVendorsService
import com.tokodizital.jajanmania.core.data.vendor.datastore.VendorSessionDataSource
import com.tokodizital.jajanmania.core.data.vendor.remote.VendorJajanManiaRemoteDataSource
import com.tokodizital.jajanmania.core.data.vendor.remote.service.VendorJajanManiaService
import com.tokodizital.jajanmania.core.domain.repository.NearbyVendorRepository
import com.tokodizital.jajanmania.core.domain.repository.VendorRepository
import com.tokodizital.jajanmania.core.domain.repository.VendorSessionRepository
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
            .client(get(named("chucker")))
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }
    single<NearbyVendorsService> {
        Retrofit.Builder()
            .baseUrl("http://103.167.151.23:3000/")
            .client(get(named("chucker")))
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }
    single { VendorJajanManiaRemoteDataSource(get()) }
    single { NearbyVendorRemoteDataSource(get()) }
    single { VendorSessionDataSource(get()) }
    single<VendorRepository> { VendorRepositoryImpl(get()) }
    single<NearbyVendorRepository> { NearbyVendorRepositoryImpl(get()) }
    single<VendorSessionRepository> { VendorSessionRepositoryImpl(get()) }

}
