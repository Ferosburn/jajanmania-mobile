package com.tokodizital.jajanmania.core.domain.repository

import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.vendor.LoginResult
import kotlinx.coroutines.flow.Flow

interface VendorRepository {

    suspend fun login(
        email: String,
        password: String
    ): Flow<Resource<LoginResult>>

}