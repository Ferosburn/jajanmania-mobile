package com.tokodizital.jajanmania.vendor.auth.login

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val errorEmailMessage: String = ""
)
