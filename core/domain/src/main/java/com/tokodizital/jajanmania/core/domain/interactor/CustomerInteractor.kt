package com.tokodizital.jajanmania.core.domain.interactor

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
import com.tokodizital.jajanmania.core.domain.usecase.CustomerUseCase
import kotlinx.coroutines.flow.Flow

class CustomerInteractor(
    private val customerRepository: CustomerRepository
) : CustomerUseCase {

    override suspend fun login(
        email: String,
        password: String
    ): Flow<Resource<CustomerLoginResult>> {
        return customerRepository.login(email, password)
    }

    override suspend fun logout(
        accountId: String,
        accountType: String,
        accessToken: String,
        refreshToken: String,
        expiredAt: String,
        firebaseToken: String
    ): Flow<Resource<CustomerLogoutResult>> {
        return customerRepository.logout(accountId, accountType, accessToken, refreshToken, expiredAt, firebaseToken)
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

    override suspend fun refreshToken(
        accountId: String,
        accountType: String,
        accessToken: String,
        refreshToken: String,
        expiredAt: String,
        firebaseToken: String
    ): Flow<Resource<CustomerRefreshTokenResult>> {
        return customerRepository.refreshToken(
            accountId,
            accountType,
            accessToken,
            refreshToken,
            expiredAt,
            firebaseToken
        )
    }

    override suspend fun getCustomerAccount(
        token: String,
        userId: String
    ): Flow<Resource<CustomerAccount>> {
        return customerRepository.getCustomerAccount(token, userId)
    }

    override suspend fun getNearbyVendors(
        latitude: Double,
        longitude: Double,
        pageNumber: Int,
        pageSize: Int,
        token: String
    ): Flow<Resource<List<NearbyVendorResult>>> {
        return customerRepository.getNearbyVendors(latitude, longitude, pageNumber, pageSize, token)
    }

    override suspend fun getVendorDetail(
        vendorId: String,
        token: String
    ): Flow<Resource<VendorDetail>> {
        return customerRepository.getVendorDetail(vendorId, token)
    }

    override suspend fun getCustomer(
        token: String,
        id: String
    ): Flow<Resource<Customer>> {
        return customerRepository.getCustomer(token, id)
    }

    override suspend fun updateCustomer(
        token: String,
        id: String,
        fullName: String,
        email: String,
        address: String,
        gender: String,
        oldPassword: String,
        newPassword: String
    ): Flow<Resource<Customer>> {
        return customerRepository.updateCustomerProfile(
            customerId = id,
            customerFullName = fullName,
            customerEmail = email,
            customerGender = gender,
            customerAddress = address,
            customerOldPassword = oldPassword,
            customerNewPassword = newPassword,
            token = token
        )
    }

    override suspend fun getMySubscriptions(
        token: String,
        pageNumber: Int,
        pageSize: Int,
        userId: String
    ): Flow<Resource<List<Category>>> {
        return customerRepository.getMySubscriptions(token, pageNumber, pageSize, userId)
    }

    override suspend fun getCategories(
        token: String,
        pageNumber: Int,
        pageSize: Int,
        userId: String
    ): Flow<Resource<List<Category>>> {
        return customerRepository.getCategories(token, pageNumber, pageSize, userId)
    }

    override suspend fun subscribe(
        token: String,
        userId: String,
        categoryId: String
    ): Flow<Resource<SubscriptionResult>> {
        return customerRepository.subscribe(token, userId, categoryId)
    }

    override suspend fun unsubscribe(
        token: String,
        userId: String,
        categoryId: String
    ): Flow<Resource<SubscriptionResult>> {
        return customerRepository.unsubscribe(token, userId, categoryId)
    }

    override suspend fun getTransactionHistory(
        token: String,
        userId: String,
        pageNumber: Int,
        pageSize: Int
    ): Flow<Resource<List<CustomerTransaction>>> {
        return customerRepository.getTransactionHistory(token, userId, pageNumber, pageSize)
    }

    override suspend fun getTransactionDetail(
        token: String,
        transactionId: String
    ): Flow<Resource<CustomerTransaction>> {
        return customerRepository.getTransactionDetail(token, transactionId)
    }

    override suspend fun topUp(
        token: String,
        userId: String,
        amount: String
    ): Flow<Resource<TopUpResult>> {
        return customerRepository.topUp(token, userId, amount)
    }
}