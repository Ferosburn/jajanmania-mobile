package com.tokodizital.jajanmania.vendor.auth.register

data class RegisterUiState(
    val shopName: String = "",
    val ownerName: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val userAgreement: Boolean = false
)
