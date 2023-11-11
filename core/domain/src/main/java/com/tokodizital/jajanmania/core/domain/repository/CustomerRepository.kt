package com.tokodizital.jajanmania.core.domain.repository

import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerLoginResult
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerRefreshTokenResult
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerRegisterResult
import com.tokodizital.jajanmania.core.domain.model.customer.NearbyVendorResult
import kotlinx.coroutines.flow.Flow

interface CustomerRepository {

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
        pageNumber: Int,
        pageSize: Int,
        token: String,
    ): Flow<Resource<List<NearbyVendorResult>>>
}