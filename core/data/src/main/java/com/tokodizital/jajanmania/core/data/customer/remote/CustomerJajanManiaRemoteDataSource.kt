package com.tokodizital.jajanmania.core.data.customer.remote

import com.haroldadmin.cnradapter.NetworkResponse
import com.tokodizital.jajanmania.core.data.customer.remote.request.CustomerLoginRequest
import com.tokodizital.jajanmania.core.data.customer.remote.request.CustomerRefreshTokenRequest
import com.tokodizital.jajanmania.core.data.customer.remote.request.CustomerRefreshTokenSessionRequest
import com.tokodizital.jajanmania.core.data.customer.remote.request.CustomerRegisterRequest
import com.tokodizital.jajanmania.core.data.customer.remote.request.CustomerUpdateProfileRequest
import com.tokodizital.jajanmania.core.data.customer.remote.request.SubscriptionRequest
import com.tokodizital.jajanmania.core.data.customer.remote.request.TopUpRequest
import com.tokodizital.jajanmania.core.data.customer.remote.response.subscription.CategoriesResponse
import com.tokodizital.jajanmania.core.data.customer.remote.response.CommonErrorResponse
import com.tokodizital.jajanmania.core.data.customer.remote.response.CustomerAccountResponse
import com.tokodizital.jajanmania.core.data.customer.remote.response.CustomerLoginResponse
import com.tokodizital.jajanmania.core.data.customer.remote.response.CustomerLogoutResponse
import com.tokodizital.jajanmania.core.data.customer.remote.response.CustomerRefreshTokenResponse
import com.tokodizital.jajanmania.core.data.customer.remote.response.CustomerRegisterResponse
import com.tokodizital.jajanmania.core.data.customer.remote.response.CustomerResponse
import com.tokodizital.jajanmania.core.data.customer.remote.response.CustomerTransactionHistoryResponse
import com.tokodizital.jajanmania.core.data.customer.remote.response.CustomerUpdateResponse
import com.tokodizital.jajanmania.core.data.customer.remote.response.subscription.MySubscriptionResponse
import com.tokodizital.jajanmania.core.data.customer.remote.response.vendor.NearbyVendorsResponse
import com.tokodizital.jajanmania.core.data.customer.remote.response.SubscriptionResponse
import com.tokodizital.jajanmania.core.data.customer.remote.response.TopUpResponse
import com.tokodizital.jajanmania.core.data.customer.remote.response.vendor.VendorsResponse
import com.tokodizital.jajanmania.core.data.customer.remote.service.CustomerJajanManiaService
import com.tokodizital.jajanmania.core.data.customer.remote.request.CustomerLogoutRequest
import com.tokodizital.jajanmania.core.data.customer.remote.request.LogoutDataSessionRequest

class CustomerJajanManiaRemoteDataSource(private val service: CustomerJajanManiaService) {

    suspend fun login(
        email: String,
        password: String,
    ): NetworkResponse<CustomerLoginResponse, CommonErrorResponse> {
        val firebaseToken = ""
        val loginRequest = CustomerLoginRequest(email, password, firebaseToken)
        return service.login(loginRequest)
    }

    suspend fun logout(
        accountId: String,
        accountType: String,
        accessToken: String,
        refreshToken: String,
        expiredAt: String,
        firebaseToken: String
    ): NetworkResponse<CustomerLogoutResponse, CommonErrorResponse> {
        val authorization = "Bearer $accessToken"
        val refreshTokenSessionRequest = LogoutDataSessionRequest(
            accessToken, refreshToken, accountType, accountId, expiredAt, firebaseToken
        )
        val refreshTokenRequest = CustomerLogoutRequest(
            session = refreshTokenSessionRequest
        )
        return service.logout(
            authorization,
            refreshTokenRequest
        )
    }
    suspend fun register(
        fullName : String,
        gender : String,
        username : String,
        email : String,
        password : String,
    ) : NetworkResponse<CustomerRegisterResponse, CommonErrorResponse> {
        val registerRequest = CustomerRegisterRequest(fullName, gender, username, email, password)
        return service.register(registerRequest)
    }

    suspend fun refreshToken(
        accountId: String,
        accountType: String,
        accessToken: String,
        refreshToken: String,
        expiredAt: String,
        firebaseToken: String,
    ): NetworkResponse<CustomerRefreshTokenResponse, CommonErrorResponse> {
        val refreshTokenRequest = CustomerRefreshTokenRequest(
            session = CustomerRefreshTokenSessionRequest(
                accountId = accountId,
                accountType = accountType,
                accessToken = accessToken,
                refreshToken = refreshToken,
                expiredAt = expiredAt,
                firebaseToken = firebaseToken,
            )
        )
        return service.refreshToken(refreshTokenRequest)
    }

    suspend fun getCustomerAccount(
        token: String,
        userId: String
    ) :NetworkResponse<CustomerAccountResponse, CommonErrorResponse> {
        val bearerToken = "Bearer $token"
        return service.getCustomerAccount(token = bearerToken, id = userId)
    }

    suspend fun getNearbyVendors(
        latitude: Double,
        longitude: Double,
        pageNumber: Int = 1,
        pageSize: Int = 10,
        token: String
    ): NetworkResponse<NearbyVendorsResponse, CommonErrorResponse> {
        val bearerToken = "Bearer $token"
        return service.getNearbyVendors(
            token = bearerToken,
            distance = 100,
            latitude = latitude,
            longitude = longitude,
            pageNumber = pageNumber,
            pageSize = pageSize
        )
    }

    suspend fun getVendorDetail(
        vendorId: String,
        token: String
    ) : NetworkResponse<VendorsResponse, CommonErrorResponse> {
        val bearerToken = "Bearer $token"
        val where = "%7B%22id%22%3A%22$vendorId%22%7D"
        val include = "%7B%22jajanItems%22%3A%7B%22include%22%3A%7B%22category%22%3Atrue%7D%7D%7D"
        return service.getVendorDetail(token = bearerToken, where = where, include = include)
    }

    suspend fun getMySubscriptions(
        token: String,
        userId: String,
        pageNumber: Int,
        pageSize: Int,
    ) : NetworkResponse<MySubscriptionResponse, CommonErrorResponse> {
        val bearerToken = "Bearer $token"
        val where = "%7B%22userSubscriptions%22%3A%7B%22some%22%3A%7B%22userId%22%3A%7B%22contains%22%3A%22$userId%22%7D%7D%7D%7D"
        return service.getMySubscriptions(
            token = bearerToken,
            pageNumber = pageNumber,
            pageSize = pageSize,
            where = where,
        )
    }

    suspend fun getCategories(
        token: String,
        userId: String,
        pageNumber: Int,
        pageSize: Int,
    ) : NetworkResponse<CategoriesResponse, CommonErrorResponse> {
        val bearerToken = "Bearer $token"
        val where = "%7B%22NOT%22%3A%7B%22userSubscriptions%22%3A%7B%22some%22%3A%7B%22userId%22%3A%7B%22contains%22%3A%22$userId%22%7D%7D%7D%7D%7D"
        return service.getCategories(
            token = bearerToken,
            pageNumber = pageNumber,
            pageSize = pageSize,
            where = where,
        )
    }

    suspend fun subscribe(
        token: String,
        userId: String,
        categoryId: String
    ): NetworkResponse<SubscriptionResponse, CommonErrorResponse> {
        val bearerToken = "Bearer $token"
        val subscriptionRequest = SubscriptionRequest(userId = userId, categoryId = categoryId)
        return service.subscribe(
            token = bearerToken,
            subscribeRequest = subscriptionRequest,
        )
    }

    suspend fun unsubscribe(
        token: String,
        userId: String,
        categoryId: String
    ): NetworkResponse<SubscriptionResponse, CommonErrorResponse> {
        val bearerToken = "Bearer $token"
        val subscriptionRequest = SubscriptionRequest(userId = userId, categoryId = categoryId)
        return service.unsubscribe(
            token = bearerToken,
            unsubscribeRequest = subscriptionRequest,
        )
    }

    suspend fun getCustomer(
        token: String,
        customerId: String
    ) : NetworkResponse<CustomerResponse, CommonErrorResponse> {
        val bearerToken = "Bearer $token"
        return service.getCustomer(token = bearerToken, customerId = customerId)
    }

    suspend fun updateCustomerProfile(
        customerId: String,
        customerFullName: String,
        customerEmail: String,
        customerGender: String,
        customerAddress: String,
        customerOldPassword: String,
        customerNewPassword: String,
        token: String
    ) : NetworkResponse<CustomerUpdateResponse, CommonErrorResponse> {
        val bearerToken = "Bearer $token"
        val request = CustomerUpdateProfileRequest(
            fullName = customerFullName,
            email = customerEmail,
            gender = customerGender,
            address = customerAddress,
            password = customerNewPassword,
            oldPassword = customerOldPassword
        )

        return service.updateCustomerProfile(token = bearerToken, customerId, customerUpdateProfileRequest = request)
    }

    suspend fun getTransactionHistory(
        token: String,
        userId: String,
        pageNumber: Int,
        pageSize: Int,
    ): NetworkResponse<CustomerTransactionHistoryResponse, CommonErrorResponse> {
        val bearerToken = "Bearer $token"
        val where = "%7B%22userId%22%3A%22$userId%22%7D"
        val include = "%7B%22transactionItems%22%3A%7B%22include%22%3A%7B%22jajanItem%22%3A%7B%22include%22%3A%7B%22vendor%22%3Atrue%7D%7D%7D%7D%7D"
        return service.getTransactionHistory(
            token = bearerToken,
            pageNumber = pageNumber,
            pageSize = pageSize,
            where = where,
            include = include
        )
    }

    suspend fun getTransactionDetail(
        token: String,
        transactionId: String,
    ): NetworkResponse<CustomerTransactionHistoryResponse, CommonErrorResponse> {
        val bearerToken = "Bearer $token"
        val where = "%7B%22id%22%3A%22$transactionId%22%7D"
        val include = "%7B%22transactionItems%22%3A%7B%22include%22%3A%7B%22jajanItem%22%3A%7B%22include%22%3A%7B%22vendor%22%3Atrue%7D%7D%7D%7D%7D"
        return service.getTransactionHistory(
            token = bearerToken,
            where = where,
            include = include
        )
    }

    suspend fun topUpCustomer(
        token: String,
        userId: String,
        amount: String
    ) : NetworkResponse<TopUpResponse, CommonErrorResponse> {
        val bearerToken = "Bearer $token"
        val topUpRequest = TopUpRequest(userId, amount)
        return service.topUps(
            token = bearerToken,
            topUpRequest = topUpRequest
        )
    }
}