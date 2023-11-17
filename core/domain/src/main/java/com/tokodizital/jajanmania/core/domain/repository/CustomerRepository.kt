package com.tokodizital.jajanmania.core.domain.repository

import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.customer.Customer
import com.tokodizital.jajanmania.core.domain.model.customer.Category
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerAccount
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerLoginResult
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerRefreshTokenResult
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerRegisterResult
import com.tokodizital.jajanmania.core.domain.model.customer.NearbyVendorResult
import com.tokodizital.jajanmania.core.domain.model.customer.SubscriptionResult
import com.tokodizital.jajanmania.core.domain.model.customer.VendorDetail
import kotlinx.coroutines.flow.Flow

interface CustomerRepository {

    suspend fun login(
        email: String,
        password: String
    ): Flow<Resource<CustomerLoginResult>>

    suspend fun register(
        fullName: String,
        gender: String,
        username: String,
        email: String,
        password: String,
    ): Flow<Resource<CustomerRegisterResult>>

    suspend fun refreshToken(
        accountId: String,
        accountType: String,
        accessToken: String,
        refreshToken: String,
        expiredAt: String,
        firebaseToken: String
    ): Flow<Resource<CustomerRefreshTokenResult>>

    suspend fun getCustomerAccount(
        token: String,
        userId: String
    ): Flow<Resource<CustomerAccount>>

    suspend fun getNearbyVendors(
        latitude: Double,
        longitude: Double,
        pageNumber: Int = 10,
        pageSize: Int = 10,
        token: String,
    ): Flow<Resource<List<NearbyVendorResult>>>

    suspend fun getVendorDetail(
        vendorId: String,
        token: String
    ) : Flow<Resource<VendorDetail>>

    suspend fun getCustomer(
        token: String,
        customerId: String
    ): Flow<Resource<Customer>>

    suspend fun updateCustomerProfile(
        customerId: String,
        customerFullName: String,
        customerEmail: String,
        customerGender: String,
        customerAddress: String,
        customerOldPassword: String,
        customerNewPassword: String,
        token: String
    ) : Flow<Resource<Customer>>

    suspend fun getMySubscriptions(
        token: String,
        pageNumber: Int = 1,
        pageSize: Int = 10,
        userId: String,
    ) : Flow<Resource<List<Category>>>

    suspend fun getCategories(
        token: String,
        pageNumber: Int = 1,
        pageSize: Int = 10,
        userId: String,
    ) : Flow<Resource<List<Category>>>

    suspend fun subscribe(
        token: String,
        userId: String,
        categoryId: String
    ) : Flow<Resource<SubscriptionResult>>

    suspend fun unsubscribe(
        token: String,
        userId: String,
        categoryId: String
    ) : Flow<Resource<SubscriptionResult>>
}