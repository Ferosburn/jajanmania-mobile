package com.tokodizital.jajanmania.core.data.vendor.remote.service

import com.haroldadmin.cnradapter.NetworkResponse
import com.tokodizital.jajanmania.core.data.vendor.remote.request.LoginRequest
import com.tokodizital.jajanmania.core.data.vendor.remote.response.CommonErrorResponse
import com.tokodizital.jajanmania.core.data.vendor.remote.response.LoginResponse
import com.tokodizital.jajanmania.core.data.vendor.remote.response.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

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

}