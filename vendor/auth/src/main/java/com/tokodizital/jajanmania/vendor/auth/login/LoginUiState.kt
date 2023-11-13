package com.tokodizital.jajanmania.vendor.auth.login

import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.vendor.LoginResult

data class LoginUiState(
    val email: String = "vendor_abdul@gmail.com",
    val password: String = "11111111",
    val errorEmailMessage: String = "",
    val errorPasswordMessage: String = "",
    val loginResult: Resource<LoginResult> = Resource.Empty
)
