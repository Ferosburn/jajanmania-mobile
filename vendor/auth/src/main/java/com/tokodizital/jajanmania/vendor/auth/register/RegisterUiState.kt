package com.tokodizital.jajanmania.vendor.auth.register

import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.vendor.RegisterResult

data class RegisterUiState(
    val fullName: String = "",
    val userName: String = "",
    val gender: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val errorEmailMessage: String = "",
    val errorFullNameMessage: String = "",
    val errorUserNameMessage: String = "",
    val errorPasswordMessage: String = "",
    val errorConfirmPasswordMessage: String = "",
    val registerResult: Resource<RegisterResult> = Resource.Empty
)
