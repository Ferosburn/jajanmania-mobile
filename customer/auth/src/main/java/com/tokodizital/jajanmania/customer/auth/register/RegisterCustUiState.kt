package com.tokodizital.jajanmania.customer.auth.register

data class RegisterCustUiState(
    val fullname: String = "",
    val email: String = "",
    val username: String = "",
    val password: String = "",
    val confirmPassword: String = "",
)
