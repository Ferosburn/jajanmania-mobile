package com.tokodizital.jajanmania.core.domain.usecase

import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerLoginResult
import kotlinx.coroutines.flow.Flow

interface CustomerUseCase {

    suspend fun login(
        email: String,
        password: String
    ): Flow<Resource<CustomerLoginResult>>
}