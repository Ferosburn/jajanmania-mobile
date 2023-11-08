package com.tokodizital.jajanmania.core.domain.interactor

import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.vendor.VendorSession
import com.tokodizital.jajanmania.core.domain.repository.VendorSessionRepository
import com.tokodizital.jajanmania.core.domain.usecase.VendorSessionUseCase
import kotlinx.coroutines.flow.Flow

class VendorSessionInteractor(
    private val vendorSessionRepository: VendorSessionRepository
) : VendorSessionUseCase {
    override val vendorSession: Flow<Resource<VendorSession>>
        get() = vendorSessionRepository.vendorSession

    override suspend fun updateVendorSession(
        accountId: String,
        accountType: String,
        accessToken: String,
        refreshToken: String,
        expiredAt: String
    ) {
        vendorSessionRepository.updateVendorSession(
            accountId,
            accountType,
            accessToken,
            refreshToken,
            expiredAt
        )
    }

    override suspend fun deleteVendorSession() {
        vendorSessionRepository.deleteVendorSession()
    }
}