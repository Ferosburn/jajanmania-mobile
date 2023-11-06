package com.tokodizital.jajanmania.core.data.customer.remote

import com.tokodizital.jajanmania.core.data.customer.remote.response.LoginResponse
import com.tokodizital.jajanmania.core.data.customer.remote.service.CustomerJajanManiaService

class CustomerJajanManiaRemoteDataSource(private val service: CustomerJajanManiaService) {

    suspend fun login(
        email: String,
        password: String
    ): LoginResponse {
        return service.login(email, password)
    }

}