package com.tokodizital.jajanmania.vendor.auth.login

import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.vendor.LoginResult

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val errorEmailMessage: String = "",
    val errorPasswordMessage: String = "",
    val loginResult: Resource<LoginResult> = Resource.Empty
)
