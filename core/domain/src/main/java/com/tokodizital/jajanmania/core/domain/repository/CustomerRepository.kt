package com.tokodizital.jajanmania.core.domain.repository

import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerLoginResult
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerRegisterResult
import kotlinx.coroutines.flow.Flow

interface CustomerRepository {

    suspend fun login(
        email: String,
        password: String
    ): Flow<Resource<CustomerLoginResult>>

    suspend fun register(
        fullName : String,
        gender : String,
        username : String,
        email : String,
        password : String,
    ): Flow<Resource<CustomerRegisterResult>>
}