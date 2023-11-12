package com.tokodizital.jajanmania.core.data

import com.haroldadmin.cnradapter.NetworkResponse
import com.tokodizital.jajanmania.core.data.customer.mapper.toDomain
import com.tokodizital.jajanmania.core.data.customer.mapper.toResult
import com.tokodizital.jajanmania.core.data.customer.remote.CustomerJajanManiaRemoteDataSource
import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerLoginResult
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerRefreshTokenResult
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerRegisterResult
import com.tokodizital.jajanmania.core.domain.model.customer.NearbyVendorResult
import com.tokodizital.jajanmania.core.domain.model.customer.VendorDetail
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

    override suspend fun refreshToken(
        accountId: String,
        accountType: String,
        accessToken: String,
        refreshToken: String,
        expiredAt: String,
        firebaseToken: String
    ): Flow<Resource<CustomerRefreshTokenResult>> = flow {
        emit(Resource.Loading)
        val refreshTokenResponse = remoteDataSource.refreshToken(
            accountId = accountId,
            accountType = accountType,
            accessToken = accessToken,
            refreshToken = refreshToken,
            expiredAt = expiredAt,
            firebaseToken = firebaseToken,
        )
        when (refreshTokenResponse) {
            is NetworkResponse.Success -> {
                val loginResult = refreshTokenResponse.body.toResult()
                emit(Resource.Success(loginResult))
            }
            is NetworkResponse.Error -> {
                val errorMessage = refreshTokenResponse.body?.message
                    ?: refreshTokenResponse.error?.message
                emit(Resource.Error(message = errorMessage.toString()))
            }
        }
    }.catch {
        emit(Resource.Error(message = it.message.toString()))
    }

    override suspend fun getNearbyVendors(
        latitude: Double,
        longitude: Double,
        pageNumber: Int,
        pageSize: Int,
        token: String
    ): Flow<Resource<List<NearbyVendorResult>>> = flow {
        emit(Resource.Loading)
        when (val nearbyVendorsResponse =
            remoteDataSource.getNearbyVendors(latitude, longitude, pageNumber, pageSize, token)) {
            is NetworkResponse.Success -> {
                val nearbyVendors = nearbyVendorsResponse.body.toResult()
                emit(Resource.Success(nearbyVendors))
            }
            is NetworkResponse.Error -> {
                val errorMessage = nearbyVendorsResponse.body?.message
                    ?: nearbyVendorsResponse.error?.message
                emit(Resource.Error(message = errorMessage.toString()))
            }
        }
    }.catch {
        emit(Resource.Error(message = it.message.toString()))
    }

    override suspend fun getVendorDetail(vendorId: String, token: String): Flow<Resource<VendorDetail>> = flow {
        emit(Resource.Loading)
        when (val vendorDetailResponse = remoteDataSource.getVendorDetail(vendorId, token)) {
            is NetworkResponse.Success -> {
                val vendorDetail = vendorDetailResponse.body.toDomain()
                emit(Resource.Success(vendorDetail))
            }
            is NetworkResponse.Error -> {
                val errorMessage =
                    vendorDetailResponse.body?.message ?: vendorDetailResponse.error?.message
                emit(Resource.Error(message = errorMessage.toString()))
            }
        }
    }.catch {
        emit(Resource.Error(message = it.message.toString()))
    }
}