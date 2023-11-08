package com.tokodizital.jajanmania.core.data

import com.haroldadmin.cnradapter.NetworkResponse
import com.tokodizital.jajanmania.core.data.vendor.mapper.toResult
import com.tokodizital.jajanmania.core.data.vendor.remote.VendorJajanManiaRemoteDataSource
import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.vendor.LoginResult
import com.tokodizital.jajanmania.core.domain.model.vendor.RegisterResult
import com.tokodizital.jajanmania.core.domain.repository.VendorRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class VendorRepositoryImpl(
    private val remoteDataSource: VendorJajanManiaRemoteDataSource
) : VendorRepository {

    override suspend fun login(
        email: String,
        password: String
    ): Flow<Resource<LoginResult>> = flow {
        emit(Resource.Loading)
        when (val loginResponse = remoteDataSource.login(email, password)) {
            is NetworkResponse.Success -> {
                val loginResult = loginResponse.body.toResult()
                emit(Resource.Success(loginResult))
            }
            is NetworkResponse.Error -> {
                val errorMessage = loginResponse.body?.message
                    ?: loginResponse.error?.message
                emit(Resource.Error(message = errorMessage.toString()))
            }
        }
    }.catch {
        emit(Resource.Error(message = it.message.toString()))
    }

    override suspend fun register(
        fullName: String,
        username: String,
        email: String,
        gender: String,
        password: String
    ): Flow<Resource<RegisterResult>> = flow {
        emit(Resource.Loading)
        val registerResponse = remoteDataSource.register(
            fullName,
            username,
            email,
            gender,
            password
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