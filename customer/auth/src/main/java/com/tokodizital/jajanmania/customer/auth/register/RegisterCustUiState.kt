package com.tokodizital.jajanmania.customer.auth.register

import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerRegisterResult

data class RegisterCustUiState(
    val fullName: String = "",
    val username: String = "",
    val email: String = "",
    val gender: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val errorFullNameMessage: String = "",
    val errorUsernameMessage: String = "",
    val errorEmailMessage: String = "",
    val errorGenderMessage: String = "",
    val errorPasswordMessage: String = "",
    val errorConfirmPasswordMessage: String = "",
    val registerResult: Resource<CustomerRegisterResult> = Resource.Empty
)
