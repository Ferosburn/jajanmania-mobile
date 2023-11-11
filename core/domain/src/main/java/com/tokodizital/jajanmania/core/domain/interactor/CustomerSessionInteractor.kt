package com.tokodizital.jajanmania.core.domain.interactor

import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerSession
import com.tokodizital.jajanmania.core.domain.repository.CustomerSessionRepository
import com.tokodizital.jajanmania.core.domain.usecase.CustomerSessionUseCase
import kotlinx.coroutines.flow.Flow

class CustomerSessionInteractor(
    private val customerSessionRepository: CustomerSessionRepository
) : CustomerSessionUseCase {

    override val customerSession: Flow<Resource<CustomerSession>>
        get() = customerSessionRepository.customerSession

    override suspend fun updateCustomerSession(
        accountId: String,
        accountType: String,
        accessToken: String,
        refreshToken: String,
        expiredAt: String,
        firebaseToken: String
    ) {
        customerSessionRepository.updateCustomerSession(
            accountId = accountId,
            accountType = accountType,
            accessToken = accessToken,
            refreshToken = refreshToken,
            expiredAt = expiredAt,
            firebaseToken = firebaseToken
        )
    }

    override suspend fun deleteCustomerSession() {
        customerSessionRepository.deleteCustomerSession()
    }
}