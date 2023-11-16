package com.tokodizital.jajanmania.core.data.vendor

import com.haroldadmin.cnradapter.NetworkResponse
import com.tokodizital.jajanmania.core.data.vendor.mapper.toDomain
import com.tokodizital.jajanmania.core.data.vendor.mapper.toResult
import com.tokodizital.jajanmania.core.data.vendor.mapper.transaction.toDomain
import com.tokodizital.jajanmania.core.data.vendor.remote.VendorJajanManiaRemoteDataSource
import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.vendor.LoginResult
import com.tokodizital.jajanmania.core.domain.model.vendor.LogoutResult
import com.tokodizital.jajanmania.core.domain.model.vendor.RefreshTokenResult
import com.tokodizital.jajanmania.core.domain.model.vendor.RegisterResult
import com.tokodizital.jajanmania.core.domain.model.vendor.Vendor
import com.tokodizital.jajanmania.core.domain.model.vendor.transaction.TransactionHistoryItem
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

    override suspend fun getTransactionHistory(
        token: String,
        page: Int,
        pageSize: Int,
        vendorId: String
    ): Flow<Resource<List<TransactionHistoryItem>>> = flow {
        emit(Resource.Loading)
        val transactionHistoryResponse = remoteDataSource.getTransactionHistory(
            token,
            page,
            pageSize,
            vendorId
        )
        when (transactionHistoryResponse) {
            is NetworkResponse.Success -> {
                val transactionHistoryResult = transactionHistoryResponse.body.data?.transactionHistories?.map {
                    it.toDomain()
                } ?: emptyList()
                emit(Resource.Success(transactionHistoryResult))
            }
            is NetworkResponse.Error -> {
                val errorMessage = transactionHistoryResponse.body?.message
                    ?: transactionHistoryResponse.error?.message
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
        expiredAt: String
    ): Flow<Resource<RefreshTokenResult>> = flow {
        emit(Resource.Loading)
        val refreshTokenResponse = remoteDataSource.refreshToken(
            accountId,
            accountType,
            accessToken,
            refreshToken,
            expiredAt
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

    override suspend fun getVendor(token: String, id: String): Flow<Resource<Vendor>> = flow {
        emit(Resource.Loading)
        when (val vendorResponse = remoteDataSource.getVendor(token, id)) {
            is NetworkResponse.Success -> {
                val vendor = vendorResponse.body.data?.toDomain() ?: Vendor()
                emit(Resource.Success(vendor))
            }
            is NetworkResponse.Error -> {
                val errorMessage = vendorResponse.body?.message
                    ?: vendorResponse.error?.message
                emit(Resource.Error(message = errorMessage.toString()))
            }
        }
    }.catch {
        emit(Resource.Error(message = it.message.toString()))
    }

    override suspend fun getShopStatus(token: String, id: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading)
        when (val vendorResponse = remoteDataSource.getVendor(token, id)) {
            is NetworkResponse.Success -> {
                val vendor = vendorResponse.body.data?.toDomain() ?: Vendor()
                val isShopActive = vendor.status.contains("ON", ignoreCase = true)
                emit(Resource.Success(isShopActive))
            }
            is NetworkResponse.Error -> {
                val errorMessage = vendorResponse.body?.message
                    ?: vendorResponse.error?.message
                emit(Resource.Error(message = errorMessage.toString()))
            }
        }
    }.catch {
        emit(Resource.Error(message = it.message.toString()))
    }

    override suspend fun updateShopStatus(
        token: String,
        id: String,
        status: Boolean,
        password: String
    ): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading)
        when (val vendorResponse = remoteDataSource.updateShopStatus(token, id, status, password)) {
            is NetworkResponse.Success -> {
                val vendor = vendorResponse.body.data?.toDomain() ?: Vendor()
                val isShopActive = vendor.status.contains("ON", ignoreCase = true)
                emit(Resource.Success(isShopActive))
            }
            is NetworkResponse.Error -> {
                val errorMessage = vendorResponse.body?.message
                    ?: vendorResponse.error?.message
                emit(Resource.Error(message = errorMessage.toString()))
            }
        }
    }.catch {
        emit(Resource.Error(message = it.message.toString()))
    }

    override suspend fun getDetailTransaction(
        token: String,
        transactionId: String
    ): Flow<Resource<TransactionHistoryItem?>> = flow {
        emit(Resource.Loading)
        val transactionHistoryResponse = remoteDataSource.getDetailTransactionHistory(
            token,
            transactionId
        )
        when (transactionHistoryResponse) {
            is NetworkResponse.Success -> {
                val transactionHistoryResult = transactionHistoryResponse.body.data?.transactionHistories?.map {
                    it.toDomain()
                }?.firstOrNull()
                emit(Resource.Success(transactionHistoryResult))
            }
            is NetworkResponse.Error -> {
                val errorMessage = transactionHistoryResponse.body?.message
                    ?: transactionHistoryResponse.error?.message
                emit(Resource.Error(message = errorMessage.toString()))
            }
        }
    }.catch {
        emit(Resource.Error(message = it.message.toString()))
    }

    override suspend fun logout(
        accountId: String,
        accountType: String,
        accessToken: String,
        refreshToken: String,
        expiredAt: String
    ): Flow<Resource<LogoutResult>> = flow {
        emit(Resource.Loading)
        val logoutResponse = remoteDataSource.logout(
            accountId,
            accountType,
            accessToken,
            refreshToken,
            expiredAt
        )
        when (logoutResponse) {
            is NetworkResponse.Success -> {
                val logoutResult = logoutResponse.body.toResult()
                emit(Resource.Success(logoutResult))
            }
            is NetworkResponse.Error -> {
                val errorMessage = logoutResponse.body?.message
                    ?: logoutResponse.error?.message
                emit(Resource.Error(message = errorMessage.toString()))
            }
        }
    }.catch {
        emit(Resource.Error(message = it.message.toString()))
    }
}