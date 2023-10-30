package com.tokodizital.jajanmania.customer.auth.login

data class LoginCustUiState(
    val email: String = "",
    val password: String = "",
    val errorEmailMessage: String = "",
    val errorPasswordMessage: String = ""
)

