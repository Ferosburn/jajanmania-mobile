package com.tokodizital.jajanmania.core.data.customer.remote.service

import com.haroldadmin.cnradapter.NetworkResponse
import com.tokodizital.jajanmania.core.data.customer.remote.request.CustomerLoginRequest
import com.tokodizital.jajanmania.core.data.customer.remote.response.CommonErrorResponse
import com.tokodizital.jajanmania.core.data.customer.remote.response.CustomerLoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface CustomerJajanManiaService {

    @POST("authentications/users/login?method=email_and_password")
    suspend fun login(
        @Body loginRequest: CustomerLoginRequest
    ): NetworkResponse<CustomerLoginResponse, CommonErrorResponse>
}