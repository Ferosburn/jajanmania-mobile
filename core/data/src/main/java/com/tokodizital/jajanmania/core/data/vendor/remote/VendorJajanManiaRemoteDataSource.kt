package com.tokodizital.jajanmania.core.data.vendor.remote

import com.tokodizital.jajanmania.core.data.vendor.remote.response.LoginResponse
import com.tokodizital.jajanmania.core.data.vendor.remote.service.VendorJajanManiaService

class VendorJajanManiaRemoteDataSource(private val service: VendorJajanManiaService) {

    suspend fun login(
        email: String,
        password: String
    ): LoginResponse {
        return service.login(email, password)
    }

}