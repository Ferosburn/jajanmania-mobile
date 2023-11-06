package com.tokodizital.jajanmania.core.data.customer

import com.tokodizital.jajanmania.core.data.customer.mapper.toResult
import com.tokodizital.jajanmania.core.data.customer.remote.CustomerJajanManiaRemoteDataSource
import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.customer.LoginResult
import com.tokodizital.jajanmania.core.domain.repository.CustomerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class CustomerRepositoryImpl(
    private val remoteDataSource: CustomerJajanManiaRemoteDataSource
) : CustomerRepository {

    override suspend fun login(
        email: String, password: String
    ): Flow<Resource<LoginResult>> = flow {
        emit(Resource.Loading)
        val loginResponse = remoteDataSource.login(email, password)
        val loginResult = loginResponse.toResult()
        emit(Resource.Success(loginResult))
    }.catch {
        emit(Resource.Error(message = it.message.toString()))
    }
}