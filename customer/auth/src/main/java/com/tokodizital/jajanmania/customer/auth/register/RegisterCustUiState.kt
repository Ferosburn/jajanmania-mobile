package com.tokodizital.jajanmania.customer.auth.register

data class RegisterCustUiState(
    val fullname: String = "",
    val email: String = "",
    val gender: String = "",
    val username: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val errorFullNameMessage: String = "",
    val errorEmailMessage: String = "",
    val errorGenderMessage: String = "",
    val errorUsernameMessage: String = "",
    val errorPasswordMessage: String = "",
    val errorConfirmPasswordMessage: String = "",
)
