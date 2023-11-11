package com.tokodizital.jajanmania.core.data

import com.tokodizital.jajanmania.core.data.customer.datastore.CustomerSessionDataSource
import com.tokodizital.jajanmania.core.data.customer.mapper.toDomain
import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerSession
import com.tokodizital.jajanmania.core.domain.repository.CustomerSessionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class CustomerSessionRepositoryImpl(
    private val customerSessionDataSource: CustomerSessionDataSource
) : CustomerSessionRepository {

    override val customerSession: Flow<Resource<CustomerSession>> = flow {
        emit(Resource.Loading)
        val customerSession = customerSessionDataSource.customerSession.map {
            Resource.Success(it.toDomain())
        }
        emitAll(customerSession)
    }.catch {
        emit(Resource.Error(it.message.toString()))
    }

    override suspend fun updateCustomerSession(
        accountId: String,
        accountType: String,
        accessToken: String,
        refreshToken: String,
        expiredAt: String,
        firebaseToken: String
    ) {
        customerSessionDataSource.updateCustomerSession(
            accountId = accountId,
            accountType = accountType,
            accessToken = accessToken,
            refreshToken = refreshToken,
            expiredAt = expiredAt,
            firebaseToken = firebaseToken
        )
    }

    override suspend fun deleteCustomerSession() {
        customerSessionDataSource.deleteCustomerSession()
    }
}
