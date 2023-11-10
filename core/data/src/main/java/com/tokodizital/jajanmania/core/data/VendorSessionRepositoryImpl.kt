package com.tokodizital.jajanmania.core.data

import com.tokodizital.jajanmania.core.data.vendor.datastore.VendorSessionDataSource
import com.tokodizital.jajanmania.core.data.vendor.mapper.toDomain
import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.vendor.VendorSession
import com.tokodizital.jajanmania.core.domain.repository.VendorSessionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class VendorSessionRepositoryImpl(
    private val vendorSessionDataSource: VendorSessionDataSource
) : VendorSessionRepository {

    override val vendorSession: Flow<Resource<VendorSession>> = flow {
        emit(Resource.Loading)
        val vendorSession = vendorSessionDataSource.vendorSession.map {
            Resource.Success(it.toDomain())
        }
        emitAll(vendorSession)
    }.catch {
        emit(Resource.Error(it.message.toString()))
    }

    override suspend fun updateVendorSession(
        accountId: String,
        accountType: String,
        accessToken: String,
        refreshToken: String,
        expiredAt: String
    ) {
        vendorSessionDataSource.updateVendorSession(
            accountId,
            accountType,
            accessToken,
            refreshToken,
            expiredAt
        )
    }

    override suspend fun deleteVendorSession() {
        vendorSessionDataSource.deleteVendorSession()
    }
}