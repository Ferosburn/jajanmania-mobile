package com.tokodizital.jajanmania.core.data.customer.remote

import com.haroldadmin.cnradapter.NetworkResponse
import com.tokodizital.jajanmania.core.data.customer.remote.request.CustomerLoginRequest
import com.tokodizital.jajanmania.core.data.customer.remote.response.CommonErrorResponse
import com.tokodizital.jajanmania.core.data.customer.remote.response.CustomerLoginResponse
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

}