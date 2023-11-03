package com.tokodizital.jajanmania.core.data.vendor.remote.service

import com.tokodizital.jajanmania.core.data.vendor.remote.response.LoginResponse
import com.tokodizital.jajanmania.core.data.vendor.remote.response.RegisterResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface VendorJajanManiaService {

    @FormUrlEncoded
    @POST("authentications/vendors/login?method=email_and_password")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @FormUrlEncoded
    @POST("authentications/vendors/register?method=email_and_password")
    suspend fun register(
        @Field("fullname") fullName: String,
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("gender") gender: String,
        @Field("password") password: String
    ): RegisterResponse

}