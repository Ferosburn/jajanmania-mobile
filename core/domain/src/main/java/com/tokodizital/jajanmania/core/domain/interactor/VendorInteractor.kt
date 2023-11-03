package com.tokodizital.jajanmania.core.domain.interactor

import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.vendor.LoginResult
import com.tokodizital.jajanmania.core.domain.model.vendor.RegisterResult
import com.tokodizital.jajanmania.core.domain.repository.VendorRepository
import com.tokodizital.jajanmania.core.domain.usecase.VendorUseCase
import kotlinx.coroutines.flow.Flow

class VendorInteractor(
    private val vendorRepository: VendorRepository
) : VendorUseCase {

    override suspend fun login(email: String, password: String): Flow<Resource<LoginResult>> {
        return vendorRepository.login(email, password)
    }

    override suspend fun register(
        fullName: String,
        username: String,
        email: String,
        gender: String,
        password: String
    ): Flow<Resource<RegisterResult>> {
        return vendorRepository.register(fullName, username, email, gender, password)
    }
}