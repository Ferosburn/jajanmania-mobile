package com.tokodizital.jajanmania.core.domain.repository

import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.vendor.LoginResult
import com.tokodizital.jajanmania.core.domain.model.vendor.RegisterResult
import kotlinx.coroutines.flow.Flow

interface VendorRepository {



    suspend fun login(
        email: String,
        password: String
    ): Flow<Resource<LoginResult>>

    suspend fun register(
        fullName: String,
        username: String,
        email: String,
        gender: String,
        password: String,
    ): Flow<Resource<RegisterResult>>



}