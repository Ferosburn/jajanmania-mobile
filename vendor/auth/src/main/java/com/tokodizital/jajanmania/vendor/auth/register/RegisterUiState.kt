package com.tokodizital.jajanmania.vendor.auth.register

data class RegisterUiState(
    val ownerName: String = "",
    val userName: String = "",
    val gender: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val errorEmailMessage: String = "",
)
