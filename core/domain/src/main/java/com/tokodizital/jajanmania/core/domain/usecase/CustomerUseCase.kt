package com.tokodizital.jajanmania.core.domain.usecase

import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.customer.Customer
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerLoginResult
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerRefreshTokenResult
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerRegisterResult
import com.tokodizital.jajanmania.core.domain.model.customer.NearbyVendorResult
import com.tokodizital.jajanmania.core.domain.model.customer.VendorDetail
import kotlinx.coroutines.flow.Flow

interface CustomerUseCase {

    suspend fun login(
        email: String,
        password: String
    ): Flow<Resource<CustomerLoginResult>>

    suspend fun register(
        fullName: String,
        gender: String,
        username: String,
        email: String,
        password: String,
    ): Flow<Resource<CustomerRegisterResult>>

    suspend fun refreshToken(
        accountId: String,
        accountType: String,
        accessToken: String,
        refreshToken: String,
        expiredAt: String,
        firebaseToken: String
    ): Flow<Resource<CustomerRefreshTokenResult>>

    suspend fun getNearbyVendors(
        latitude: Double,
        longitude: Double,
        pageNumber: Int = 1,
        pageSize: Int = 10,
        token: String
    ): Flow<Resource<List<NearbyVendorResult>>>

    suspend fun getVendorDetail(
        vendorId: String,
        token: String
    ) : Flow<Resource<VendorDetail>>

    suspend fun getCustomer(
        token: String,
        id: String
    ): Flow<Resource<Customer>>

    suspend fun updateCustomer(
        token: String,
        id: String,
        fullName: String,
        address: String,
        gender: String,
        oldPassword: String,
        newPassword: String
    ): Flow<Resource<Customer>>

}