package com.tokodizital.jajanmania.core.data.vendor.remote

import com.haroldadmin.cnradapter.NetworkResponse
import com.tokodizital.jajanmania.core.data.vendor.remote.request.LoginRequest
import com.tokodizital.jajanmania.core.data.vendor.remote.response.CommonErrorResponse
import com.tokodizital.jajanmania.core.data.vendor.remote.response.LoginResponse
import com.tokodizital.jajanmania.core.data.vendor.remote.response.RegisterResponse
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

}