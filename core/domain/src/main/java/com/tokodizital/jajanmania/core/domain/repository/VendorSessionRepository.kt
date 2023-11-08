package com.tokodizital.jajanmania.core.domain.repository

import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.vendor.VendorSession
import kotlinx.coroutines.flow.Flow

interface VendorSessionRepository {

    val vendorSession: Flow<Resource<VendorSession>>

    suspend fun updateVendorSession(
        accountId: String,
        accountType: String,
        accessToken: String,
        refreshToken: String,
        expiredAt: String
    )

    suspend fun deleteVendorSession()

}