package com.tokodizital.jajanmania.core.domain.interactor

import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.vendor.LoginResult
import com.tokodizital.jajanmania.core.domain.model.vendor.RefreshTokenResult
import com.tokodizital.jajanmania.core.domain.model.vendor.RegisterResult
import com.tokodizital.jajanmania.core.domain.model.vendor.Vendor
import com.tokodizital.jajanmania.core.domain.model.vendor.transaction.TransactionHistoryItem
import com.tokodizital.jajanmania.core.domain.repository.VendorRepository
import com.tokodizital.jajanmania.core.domain.usecase.VendorUseCase
import kotlinx.coroutines.flow.Flow

class VendorInteractor(
    private val vendorRepository: VendorRepository
) : VendorUseCase {

    override suspend fun login(email: String, password: String): Flow<Resource<LoginResult>> {
        return vendorRepository.login(email, password)
    }

    override suspend fun register(
        fullName: String,
        username: String,
        email: String,
        gender: String,
        password: String
    ): Flow<Resource<RegisterResult>> {
        return vendorRepository.register(fullName, username, email, gender, password)
    }

    override suspend fun getTransactionHistory(
        token: String,
        page: Int,
        pageSize: Int,
        vendorId: String
    ): Flow<Resource<List<TransactionHistoryItem>>> {
        return vendorRepository.getTransactionHistory(token, page, pageSize, vendorId)
    }

    override suspend fun refreshToken(
        accountId: String,
        accountType: String,
        accessToken: String,
        refreshToken: String,
        expiredAt: String
    ): Flow<Resource<RefreshTokenResult>> {
        return vendorRepository.refreshToken(
            accountId,
            accountType,
            accessToken,
            refreshToken,
            expiredAt
        )
    }

    override suspend fun getVendor(token: String, id: String): Flow<Resource<Vendor>> {
        return vendorRepository.getVendor(token, id)
    }

    override suspend fun getShopStatus(token: String, id: String): Flow<Resource<Boolean>> {
        return vendorRepository.getShopStatus(token, id)
    }

    override suspend fun updateShopStatus(
        token: String,
        id: String,
        status: Boolean
    ): Flow<Resource<Boolean>> {
        TODO("Not yet implemented")
    }
}