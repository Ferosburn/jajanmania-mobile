package com.tokodizital.jajanmania.core.data.vendor.remote.service

import com.haroldadmin.cnradapter.NetworkResponse
import com.tokodizital.jajanmania.core.data.vendor.remote.request.LoginRequest
import com.tokodizital.jajanmania.core.data.vendor.remote.request.RefreshTokenRequest
import com.tokodizital.jajanmania.core.data.vendor.remote.request.UpdateShopStatusRequest
import com.tokodizital.jajanmania.core.data.vendor.remote.response.CommonErrorResponse
import com.tokodizital.jajanmania.core.data.vendor.remote.response.LoginResponse
import com.tokodizital.jajanmania.core.data.vendor.remote.response.RefreshTokenResponse
import com.tokodizital.jajanmania.core.data.vendor.remote.response.RegisterResponse
import com.tokodizital.jajanmania.core.data.vendor.remote.response.VendorResponse
import com.tokodizital.jajanmania.core.data.vendor.remote.response.transaction.TransactionHistoryResponse
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface VendorJajanManiaService {

    @POST("authentications/vendors/login?method=email_and_password")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): NetworkResponse<LoginResponse, CommonErrorResponse>

    @FormUrlEncoded
    @POST("authentications/vendors/register?method=email_and_password")
    suspend fun register(
        @Field("fullName") fullName: String,
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("gender") gender: String,
        @Field("password") password: String,
        @Field("address") address: String = "",
        @Field("lastLatitude") lastLatitude: Double = 0.0,
        @Field("lastLongitude") lastLongitude: Double = 0.0,
        @Field("jajanImageUrl") jajanImageUrl: String = "",
        @Field("jajanName") jajanName: String = "",
        @Field("jajanDescription") jajanDescription: String = "",
    ): NetworkResponse<RegisterResponse, CommonErrorResponse>

    @GET("transaction-histories")
    suspend fun getTransactionHistory(
        @Header("authorization") token: String,
        @Query("page_number") page: Int = 1,
        @Query("page_size") pageSize: Int = 10,
        @Query("where") where: String,
        @Query("include") include: String
    ): NetworkResponse<TransactionHistoryResponse, CommonErrorResponse>

    @POST("authentications/vendors/refreshes/access-token")
    suspend fun refreshToken(
        @Body refreshTokenRequest: RefreshTokenRequest
    ): NetworkResponse<RefreshTokenResponse, CommonErrorResponse>

    @GET("vendors/{id}")
    suspend fun getVendor(
        @Header("authorization") token: String,
        @Path("id") id: String
    ): NetworkResponse<VendorResponse, CommonErrorResponse>

    @PATCH("vendors/{id}")
    suspend fun updateShopStatus(
        @Header("authorization") token: String,
        @Path("id") id: String,
        @Body updateShopStatusRequest: UpdateShopStatusRequest
    ): NetworkResponse<VendorResponse, CommonErrorResponse>

}