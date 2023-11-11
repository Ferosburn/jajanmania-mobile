package com.tokodizital.jajanmania.core.data

import com.haroldadmin.cnradapter.NetworkResponse
import com.tokodizital.jajanmania.core.data.customer.mapper.toResult
import com.tokodizital.jajanmania.core.data.customer.remote.CustomerJajanManiaRemoteDataSource
import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerLoginResult
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerRegisterResult
import com.tokodizital.jajanmania.core.domain.repository.CustomerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class CustomerRepositoryImpl(
    private val remoteDataSource: CustomerJajanManiaRemoteDataSource
) : CustomerRepository {

    override suspend fun login(
        email: String, password: String
    ): Flow<Resource<CustomerLoginResult>> = flow {
        emit(Resource.Loading)
        when (val loginResponse = remoteDataSource.login(email, password)) {
            is NetworkResponse.Success -> {
                val loginResult = loginResponse.body.toResult()
                emit(Resource.Success(loginResult))
            }
            is NetworkResponse.Error -> {
                val errorMessage = loginResponse.body?.message ?: loginResponse.error?.message
                emit(Resource.Error(message = errorMessage.toString()))
            }
        }
    }.catch {
        emit(Resource.Error(message = it.message.toString()))
    }

    override suspend fun register(
        fullName: String,
        gender: String,
        username: String,
        email: String,
        password: String
    ): Flow<Resource<CustomerRegisterResult>> = flow {
        emit(Resource.Loading)
        val registerResponse = remoteDataSource.register(
            fullName = fullName,
            gender = gender,
            username = username,
            email = email,
            password = password,
        )
        when (registerResponse) {
            is NetworkResponse.Success -> {
                val registerResult = registerResponse.body.toResult()
                emit(Resource.Success(registerResult))
            }
            is NetworkResponse.Error -> {
                val errorMessage = registerResponse.body?.message
                    ?: registerResponse.error?.message
                emit(Resource.Error(message = errorMessage.toString()))
            }
        }
    }.catch {
        emit(Resource.Error(message = it.message.toString()))
    }
}