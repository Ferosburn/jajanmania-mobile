package com.tokodizital.jajanmania.core.domain.repository

import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerSession
import kotlinx.coroutines.flow.Flow

interface CustomerSessionRepository {
    val customerSession: Flow<Resource<CustomerSession>>

    suspend fun updateCustomerSession(
        accountId: String,
        accountType: String,
        accessToken: String,
        refreshToken: String,
        expiredAt: String,
        firebaseToken: String,
    )

    suspend fun deleteCustomerSession()
}