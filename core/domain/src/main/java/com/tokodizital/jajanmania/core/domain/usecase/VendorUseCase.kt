package com.tokodizital.jajanmania.core.domain.usecase

import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.vendor.LoginResult
import kotlinx.coroutines.flow.Flow

interface VendorUseCase {

    suspend fun login(
        email: String,
        password: String
    ): Flow<Resource<LoginResult>>

}