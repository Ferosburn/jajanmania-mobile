package com.tokodizital.jajanmania.core.data.di

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.GsonBuilder
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import com.tokodizital.jajanmania.core.data.CustomerRepositoryImpl
import com.tokodizital.jajanmania.core.data.CustomerSessionRepositoryImpl
import com.tokodizital.jajanmania.core.data.VendorRepositoryImpl
import com.tokodizital.jajanmania.core.data.VendorSessionRepositoryImpl
import com.tokodizital.jajanmania.core.data.customer.datastore.CustomerSessionDataSource
import com.tokodizital.jajanmania.core.data.customer.remote.CustomerJajanManiaRemoteDataSource
import com.tokodizital.jajanmania.core.data.customer.remote.service.CustomerJajanManiaService
import com.tokodizital.jajanmania.core.data.customer.VendorDetailRepositoryImpl
import com.tokodizital.jajanmania.core.data.customer.remote.VendorDetailRemoteDataSource
import com.tokodizital.jajanmania.core.data.customer.remote.service.VendorDetailService
import com.tokodizital.jajanmania.core.data.vendor.datastore.VendorSessionDataSource
import com.tokodizital.jajanmania.core.data.vendor.remote.VendorJajanManiaRemoteDataSource
import com.tokodizital.jajanmania.core.data.vendor.remote.service.VendorJajanManiaService
import com.tokodizital.jajanmania.core.domain.repository.CustomerRepository
import com.tokodizital.jajanmania.core.domain.repository.CustomerSessionRepository
import com.tokodizital.jajanmania.core.domain.repository.VendorDetailRepository
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

    single(named("gson-lenient")) { GsonBuilder().setLenient().create() }

    single<VendorJajanManiaService> {
        Retrofit.Builder()
            .baseUrl("http://103.167.151.23:3000/api/v1/")
            .client(get(named("chucker")))
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(get(named("gson-lenient"))))
            .build()
            .create()
    }
    single<CustomerJajanManiaService> {
        Retrofit.Builder()
            .baseUrl("http://103.167.151.23:3000/api/v1/")
            .client(get(named("chucker")))
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(get(named("gson-lenient"))))
            .build()
            .create()
    }
    single<VendorDetailService> {
        Retrofit.Builder()
            .baseUrl("http://103.167.151.23:3000/")
            .client(get(named("chucker")))
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }
    single { VendorJajanManiaRemoteDataSource(get()) }
    single { VendorSessionDataSource(get()) }
    single { VendorDetailRemoteDataSource(get()) }
    single { CustomerJajanManiaRemoteDataSource(get()) }
    single { CustomerSessionDataSource(get()) }
    single<VendorRepository> { VendorRepositoryImpl(get()) }
    single<VendorSessionRepository> { VendorSessionRepositoryImpl(get()) }
    single<VendorDetailRepository> { VendorDetailRepositoryImpl(get()) }
    single<CustomerRepository> { CustomerRepositoryImpl(get()) }
    single<CustomerSessionRepository> { CustomerSessionRepositoryImpl(get()) }
}
