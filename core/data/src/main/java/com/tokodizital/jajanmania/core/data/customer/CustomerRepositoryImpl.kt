package com.tokodizital.jajanmania.core.data.customer

import com.haroldadmin.cnradapter.NetworkResponse
import com.tokodizital.jajanmania.core.data.customer.mapper.toDomain
import com.tokodizital.jajanmania.core.data.customer.mapper.toResult
import com.tokodizital.jajanmania.core.data.customer.remote.CustomerJajanManiaRemoteDataSource
import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.customer.Category
import com.tokodizital.jajanmania.core.domain.model.customer.Customer
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerAccount
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerLoginResult
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerLogoutResult
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerRefreshTokenResult
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerRegisterResult
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerTransaction
import com.tokodizital.jajanmania.core.domain.model.customer.NearbyVendorResult
import com.tokodizital.jajanmania.core.domain.model.customer.SubscriptionResult
import com.tokodizital.jajanmania.core.domain.model.customer.TopUpResult
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

    override suspend fun logout(
        accountId: String,
        accountType: String,
        accessToken: String,
        refreshToken: String,
        expiredAt: String,
        firebaseToken: String
    ): Flow<Resource<CustomerLogoutResult>> = flow {
        emit(Resource.Loading)
        val logoutResponse = remoteDataSource.logout(
            accountId,
            accountType,
            accessToken,
            refreshToken,
            expiredAt,
            firebaseToken
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

    override suspend fun getCustomerAccount(
        token: String,
        userId: String
    ): Flow<Resource<CustomerAccount>> = flow {
        emit(Resource.Loading)
        val customerAccountResponse = remoteDataSource.getCustomerAccount(
            token = token,
            userId = userId
        )
        when (customerAccountResponse) {
            is NetworkResponse.Success -> {
                val customerAccount = customerAccountResponse.body.toDomain()
                emit(Resource.Success(customerAccount))
            }
            is NetworkResponse.Error -> {
                val errorMessage = customerAccountResponse.body?.message
                    ?: customerAccountResponse.error?.message
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

    override suspend fun getVendorDetail(
        vendorId: String,
        token: String
    ): Flow<Resource<VendorDetail>> = flow {
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

    override suspend fun getMySubscriptions(
        token: String,
        pageNumber: Int,
        pageSize: Int,
        userId: String
    ): Flow<Resource<List<Category>>> = flow {
        emit(Resource.Loading)
        val mySubscriptionResponse = remoteDataSource.getMySubscriptions(
            token = token,
            userId = userId,
            pageNumber = pageNumber,
            pageSize = pageSize,
        )
        when (mySubscriptionResponse) {
            is NetworkResponse.Success -> {
                val mySubscription = mySubscriptionResponse.body.toDomain()
                emit(Resource.Success(mySubscription))
            }
            is NetworkResponse.Error -> {
                val errorMessage =
                    mySubscriptionResponse.body?.message ?: mySubscriptionResponse.error?.message
                emit(Resource.Error(message = errorMessage.toString()))
            }
        }
    }.catch {
        emit(Resource.Error(message = it.message.toString()))
    }

    override suspend fun getCategories(
        token: String,
        pageNumber: Int,
        pageSize: Int,
        userId: String
    ): Flow<Resource<List<Category>>> = flow {
        emit(Resource.Loading)
        val categoriesResponse = remoteDataSource.getCategories(
            token = token,
            userId = userId,
            pageNumber = pageNumber,
            pageSize = pageSize,
        )
        when (categoriesResponse) {
            is NetworkResponse.Success -> {
                val categories = categoriesResponse.body.toDomain()
                emit(Resource.Success(categories))
            }
            is NetworkResponse.Error -> {
                val errorMessage =
                    categoriesResponse.body?.message ?: categoriesResponse.error?.message
                emit(Resource.Error(message = errorMessage.toString()))
            }
        }
    }.catch {
        emit(Resource.Error(message = it.message.toString()))
    }

    override suspend fun subscribe(
        token: String,
        userId: String,
        categoryId: String
    ): Flow<Resource<SubscriptionResult>> = flow {
        emit(Resource.Loading)
        when (val subscribeResponse = remoteDataSource.subscribe(token, userId, categoryId)) {
            is NetworkResponse.Success -> {
                val response = subscribeResponse.body.toResult()
                emit(Resource.Success(response))
            }
            is NetworkResponse.Error -> {
                val errorMessage =
                    subscribeResponse.body?.message ?: subscribeResponse.error?.message
                emit(Resource.Error(message = errorMessage.toString()))
            }
        }
    }.catch {
        emit(Resource.Error(message = it.message.toString()))
    }

    override suspend fun unsubscribe(
        token: String,
        userId: String,
        categoryId: String
    ): Flow<Resource<SubscriptionResult>> = flow {
        emit(Resource.Loading)
        when (val subscribeResponse = remoteDataSource.unsubscribe(token, userId, categoryId)) {
            is NetworkResponse.Success -> {
                val response = subscribeResponse.body.toResult()
                emit(Resource.Success(response))
            }
            is NetworkResponse.Error -> {
                val errorMessage =
                    subscribeResponse.body?.message ?: subscribeResponse.error?.message
                emit(Resource.Error(message = errorMessage.toString()))
            }
        }
    }.catch {
        emit(Resource.Error(message = it.message.toString()))
    }

    override suspend fun getCustomer(token: String, customerId: String): Flow<Resource<Customer>> = flow {
        emit(Resource.Loading)
        when (val customerResponse = remoteDataSource.getCustomer(token, customerId)) {
            is NetworkResponse.Success -> {
                val customer = customerResponse.body.toResult()
                emit(Resource.Success(customer))
            }
            is NetworkResponse.Error -> {
                val errorMessage =
                    customerResponse.body?.message ?: customerResponse.error?.message
                emit(Resource.Error(message = errorMessage.toString()))
            }
        }
    }.catch {
        emit(Resource.Error(message = it.message.toString()))
    }

    override suspend fun updateCustomerProfile(
        customerId: String,
        customerFullName: String,
        customerEmail: String,
        customerGender: String,
        customerAddress: String,
        customerOldPassword: String,
        customerNewPassword: String,
        token: String
    ): Flow<Resource<Customer>> = flow {
        emit(Resource.Loading)
        val updateCustomerProfileResponse = remoteDataSource.updateCustomerProfile(
            customerId, customerFullName, customerEmail, customerGender, customerAddress, customerOldPassword, customerNewPassword, token
        )

        when (updateCustomerProfileResponse) {
            is NetworkResponse.Success -> {
                val customer = updateCustomerProfileResponse.body.toResult()
                emit(Resource.Success(customer))
            }
            is NetworkResponse.Error -> {
                val errorMessage = updateCustomerProfileResponse.body?.message ?: updateCustomerProfileResponse.error?.message
                emit(Resource.Error(message = errorMessage.toString()))
            }
        }
    }.catch {
        emit(Resource.Error(message = it.message.toString()))
    }

    override suspend fun getTransactionHistory(
        token: String,
        userId: String,
        pageNumber: Int,
        pageSize: Int
    ): Flow<Resource<List<CustomerTransaction>>> = flow {
        emit(Resource.Loading)
        val transactionResponse = remoteDataSource.getTransactionHistory(
            token = token,
            userId = userId,
            pageNumber = pageNumber,
            pageSize = pageSize,
        )
        when (transactionResponse) {
            is NetworkResponse.Success -> {
                val response = transactionResponse.body.toDomain()
                emit(Resource.Success(response))
            }
            is NetworkResponse.Error -> {
                val errorMessage =
                    transactionResponse.body?.message ?: transactionResponse.error?.message
                emit(Resource.Error(message = errorMessage.toString()))
            }
        }
    }.catch {
        emit(Resource.Error(message = it.message.toString()))
    }

    override suspend fun getTransactionDetail(
        token: String,
        transactionId: String
    ): Flow<Resource<CustomerTransaction>> = flow {
        emit(Resource.Loading)
        val transactionDetailResponse = remoteDataSource.getTransactionDetail(
            token = token,
            transactionId = transactionId,
        )
        when (transactionDetailResponse) {
            is NetworkResponse.Success -> {
                val response = transactionDetailResponse.body.toResult()
                emit(Resource.Success(response))
            }
            is NetworkResponse.Error -> {
                val errorMessage =
                    transactionDetailResponse.body?.message ?: transactionDetailResponse.error?.message
                emit(Resource.Error(message = errorMessage.toString()))
            }
        }
    }.catch {
        emit(Resource.Error(message = it.message.toString()))
    }

    override suspend fun topUp(
        token: String,
        userId: String,
        amount: String
    ): Flow<Resource<TopUpResult>> = flow {
        emit(Resource.Loading)
        val topUpResponse = remoteDataSource.topUpCustomer(
            token = token,
            userId = userId,
            amount = amount
        )
        when (topUpResponse) {
            is NetworkResponse.Success -> {
                val response = topUpResponse.body.toResult()
                emit(Resource.Success(response))
            }
            is NetworkResponse.Error -> {
                val errorMessage =
                    topUpResponse.body?.message ?: topUpResponse.error?.message
                emit(Resource.Error(message = errorMessage.toString()))
            }
        }
    }
}