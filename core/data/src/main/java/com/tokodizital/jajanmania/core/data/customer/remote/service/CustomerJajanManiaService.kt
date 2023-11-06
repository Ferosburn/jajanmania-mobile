package com.tokodizital.jajanmania.core.data.customer.remote.service

import com.tokodizital.jajanmania.core.data.customer.remote.response.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface CustomerJajanManiaService {

    @FormUrlEncoded
    @POST("authentications/users/login?method=email_and_password")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse
}