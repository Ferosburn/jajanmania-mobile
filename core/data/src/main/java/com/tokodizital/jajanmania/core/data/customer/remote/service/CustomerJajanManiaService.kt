package com.tokodizital.jajanmania.core.data.customer.remote.service

import com.haroldadmin.cnradapter.NetworkResponse
import com.tokodizital.jajanmania.core.data.customer.remote.request.CustomerLoginRequest
import com.tokodizital.jajanmania.core.data.customer.remote.request.CustomerRefreshTokenRequest
import com.tokodizital.jajanmania.core.data.customer.remote.request.CustomerRegisterRequest
import com.tokodizital.jajanmania.core.data.customer.remote.response.CommonErrorResponse
import com.tokodizital.jajanmania.core.data.customer.remote.response.CustomerLoginResponse
import com.tokodizital.jajanmania.core.data.customer.remote.response.CustomerRefreshTokenResponse
import com.tokodizital.jajanmania.core.data.customer.remote.response.CustomerRegisterResponse
import com.tokodizital.jajanmania.core.data.customer.remote.response.NearbyVendorsResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface CustomerJajanManiaService {

    @POST("authentications/users/login?method=email_and_password")
    suspend fun login(
        @Body loginRequest: CustomerLoginRequest
    ): NetworkResponse<CustomerLoginResponse, CommonErrorResponse>

    @POST("authentications/users/register?method=email_and_password")
    suspend fun register(
        @Body registerRequest: CustomerRegisterRequest
    ) : NetworkResponse<CustomerRegisterResponse, CommonErrorResponse>

    @POST("authentications/users/refreshes/access-token")
    suspend fun refreshToken(
        @Body refreshTokenRequest: CustomerRefreshTokenRequest
    ): NetworkResponse<CustomerRefreshTokenResponse, CommonErrorResponse>

    @GET("vendors/locations")
    suspend fun getNearbyVendors(
        @Header("Authorization") token: String,
        @Query("distance") distance: Int = 100,
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("page_number") pageNumber: Int = 1,
        @Query("page_size") pageSize: Int = 10,
    ): NetworkResponse<NearbyVendorsResponse, CommonErrorResponse>
}