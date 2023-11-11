package com.tokodizital.jajanmania.core.data.vendor.remote

import com.haroldadmin.cnradapter.NetworkResponse
import com.tokodizital.jajanmania.core.data.vendor.remote.request.LoginRequest
import com.tokodizital.jajanmania.core.data.vendor.remote.request.RefreshTokenRequest
import com.tokodizital.jajanmania.core.data.vendor.remote.request.RefreshTokenSessionRequest
import com.tokodizital.jajanmania.core.data.vendor.remote.request.UpdateShopStatusRequest
import com.tokodizital.jajanmania.core.data.vendor.remote.response.CommonErrorResponse
import com.tokodizital.jajanmania.core.data.vendor.remote.response.LoginResponse
import com.tokodizital.jajanmania.core.data.vendor.remote.response.RefreshTokenResponse
import com.tokodizital.jajanmania.core.data.vendor.remote.response.RegisterResponse
import com.tokodizital.jajanmania.core.data.vendor.remote.response.VendorResponse
import com.tokodizital.jajanmania.core.data.vendor.remote.response.transaction.TransactionHistoryResponse
import com.tokodizital.jajanmania.core.data.vendor.remote.service.VendorJajanManiaService

class VendorJajanManiaRemoteDataSource(private val service: VendorJajanManiaService) {

    suspend fun login(
        email: String,
        password: String
    ): NetworkResponse<LoginResponse, CommonErrorResponse> {
        val loginRequest = LoginRequest(email, password)
        return service.login(loginRequest)
    }

    suspend fun register(
        fullName: String,
        username: String,
        email: String,
        gender: String,
        password: String
    ): NetworkResponse<RegisterResponse, CommonErrorResponse> {
        return service.register(fullName, username, email, gender, password)
    }

    suspend fun getTransactionHistory(
        token: String,
        page: Int = 1,
        pageSize: Int = 10,
        vendorId: String,
    ): NetworkResponse<TransactionHistoryResponse, CommonErrorResponse> {
        val authorization = "Bearer $token"
        val whereParams = "%7B%22transactionItems%22%3A%20%7B%22some%22%3A%20%7B%22jajanItem%22%3A%20%7B%22is%22%3A%20%7B%22vendor%22%3A%20%7B%22is%22%3A%20%7B%22id%22%3A%20%22$vendorId%22%7D%7D%7D%7D%7D%7D%7D"
        val includeParams = "%7B%22transactionItems%22%3A%20%7B%22include%22%20%3A%20%7B%22jajanItem%22%3A%20true%7D%7D%7D"
        return service.getTransactionHistory(
            authorization,
            page,
            pageSize,
            whereParams,
            includeParams
        )
    }

    suspend fun refreshToken(
        accountId: String,
        accountType: String,
        accessToken: String,
        refreshToken: String,
        expiredAt: String
    ): NetworkResponse<RefreshTokenResponse, CommonErrorResponse> {
        val refreshTokenSessionRequest = RefreshTokenSessionRequest(
            accessToken, refreshToken, accountType, accountId, expiredAt
        )
        val refreshTokenRequest = RefreshTokenRequest(
            session = refreshTokenSessionRequest
        )
        return service.refreshToken(
            refreshTokenRequest
        )
    }

    suspend fun getVendor(
        token: String,
        id: String
    ): NetworkResponse<VendorResponse, CommonErrorResponse> {
        val authorization = "Bearer $token"
        return service.getVendor(authorization, id)
    }

    suspend fun updateShopStatus(
        token: String,
        id: String,
        status: Boolean,
        password: String
    ): NetworkResponse<VendorResponse, CommonErrorResponse> {
        val authorization = "Bearer $token"
        val shopStatus = if (status) "ON" else "OFF"
        val updateShopStatusRequest = UpdateShopStatusRequest(
            password, password, shopStatus
        )
        return service.updateShopStatus(authorization, id, updateShopStatusRequest)
    }

}