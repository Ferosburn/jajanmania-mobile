package com.tokodizital.jajanmania.customer.auth.login

import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerLoginResult

data class LoginCustUiState(
    val email: String = "bla@email.com",
    val password: String = "11111111",
    val errorEmailMessage: String = "",
    val errorPasswordMessage: String = "",
    val loginResult: Resource<CustomerLoginResult> = Resource.Empty
)

