package com.tokodizital.jajanmania.core.data

import com.tokodizital.jajanmania.core.data.vendor.mapper.toResult
import com.tokodizital.jajanmania.core.data.vendor.remote.VendorJajanManiaRemoteDataSource
import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.vendor.LoginResult
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
    ): Flow<Resource<LoginResult>> = flow<Resource<LoginResult>> {
        emit(Resource.Loading)
        val loginResponse = remoteDataSource.login(email, password)
        val loginResult = loginResponse.toResult()
        emit(Resource.Success(loginResult))
    }.catch {
        emit(Resource.Error(message = it.message.toString()))
    }

}