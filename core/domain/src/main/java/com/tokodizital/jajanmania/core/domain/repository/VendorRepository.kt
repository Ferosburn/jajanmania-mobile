package com.tokodizital.jajanmania.core.domain.repository

import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.vendor.LoginResult
import com.tokodizital.jajanmania.core.domain.model.vendor.RefreshTokenResult
import com.tokodizital.jajanmania.core.domain.model.vendor.RegisterResult
import com.tokodizital.jajanmania.core.domain.model.vendor.Vendor
import com.tokodizital.jajanmania.core.domain.model.vendor.transaction.TransactionHistoryItem
import kotlinx.coroutines.flow.Flow

interface VendorRepository {

    suspend fun login(
        email: String,
        password: String
    ): Flow<Resource<LoginResult>>

    suspend fun register(
        fullName: String,
        username: String,
        email: String,
        gender: String,
        password: String,
    ): Flow<Resource<RegisterResult>>

    suspend fun getTransactionHistory(
        token: String,
        page: Int = 1,
        pageSize: Int = 10,
        vendorId: String
    ): Flow<Resource<List<TransactionHistoryItem>>>

    suspend fun refreshToken(
        accountId: String,
        accountType: String,
        accessToken: String,
        refreshToken: String,
        expiredAt: String
    ): Flow<Resource<RefreshTokenResult>>

    suspend fun getVendor(
        token: String,
        id: String
    ): Flow<Resource<Vendor>>

}