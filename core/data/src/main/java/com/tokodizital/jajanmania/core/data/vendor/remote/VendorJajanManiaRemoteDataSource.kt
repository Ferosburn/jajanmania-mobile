package com.tokodizital.jajanmania.core.data.vendor.remote

import com.tokodizital.jajanmania.core.data.vendor.remote.response.LoginResponse
import com.tokodizital.jajanmania.core.data.vendor.remote.response.RegisterResponse
import com.tokodizital.jajanmania.core.data.vendor.remote.service.VendorJajanManiaService

class VendorJajanManiaRemoteDataSource(private val service: VendorJajanManiaService) {

    suspend fun register(
        email: String,
        password: String
    ): LoginResponse {
        return service.login(email, password)
    }

    suspend fun register(
        fullName: String,
        username: String,
        email: String,
        gender: String,
        password: String
    ): RegisterResponse {
        return service.register(fullName, username, email, gender, password)
    }

}