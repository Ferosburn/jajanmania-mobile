package com.tokodizital.jajanmania.core.data.customer.remote

import com.haroldadmin.cnradapter.NetworkResponse
import com.tokodizital.jajanmania.core.data.customer.remote.request.CustomerLoginRequest
import com.tokodizital.jajanmania.core.data.customer.remote.request.CustomerRefreshTokenRequest
import com.tokodizital.jajanmania.core.data.customer.remote.request.CustomerRefreshTokenSessionRequest
import com.tokodizital.jajanmania.core.data.customer.remote.request.CustomerRegisterRequest
import com.tokodizital.jajanmania.core.data.customer.remote.response.CommonErrorResponse
import com.tokodizital.jajanmania.core.data.customer.remote.response.CustomerLoginResponse
import com.tokodizital.jajanmania.core.data.customer.remote.response.CustomerRefreshTokenResponse
import com.tokodizital.jajanmania.core.data.customer.remote.response.CustomerRegisterResponse
import com.tokodizital.jajanmania.core.data.customer.remote.response.NearbyVendorsResponse
import com.tokodizital.jajanmania.core.data.customer.remote.response.VendorsResponse
import com.tokodizital.jajanmania.core.data.customer.remote.service.CustomerJajanManiaService

class CustomerJajanManiaRemoteDataSource(private val service: CustomerJajanManiaService) {

    suspend fun login(
        email: String,
        password: String,
    ): NetworkResponse<CustomerLoginResponse, CommonErrorResponse> {
        val firebaseToken = ""
        val loginRequest = CustomerLoginRequest(email, password, firebaseToken)
        return service.login(loginRequest)
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
}