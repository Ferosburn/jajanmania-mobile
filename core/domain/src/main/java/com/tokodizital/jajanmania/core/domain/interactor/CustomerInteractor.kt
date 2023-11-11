package com.tokodizital.jajanmania.core.domain.interactor

import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerLoginResult
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerRegisterResult
import com.tokodizital.jajanmania.core.domain.repository.CustomerRepository
import com.tokodizital.jajanmania.core.domain.usecase.CustomerUseCase
import kotlinx.coroutines.flow.Flow

class CustomerInteractor(
    private val customerRepository: CustomerRepository
) : CustomerUseCase {

    override suspend fun login(email: String, password: String): Flow<Resource<CustomerLoginResult>> {
        return customerRepository.login(email, password)
    }

    override suspend fun register(
        fullName: String,
        gender: String,
        username: String,
        email: String,
        password: String
    ): Flow<Resource<CustomerRegisterResult>> {
        return customerRepository.register(fullName, gender, username, email, password)
    }

}