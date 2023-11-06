package com.tokodizital.jajanmania.customer.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tokodizital.jajanmania.common.utils.isValidEmail
import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.usecase.CustomerUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginCustViewModel(
    private val customerUseCase: CustomerUseCase
) : ViewModel() {

    private val _loginCustUiState = MutableStateFlow(LoginCustUiState())
    val loginCustUiState: StateFlow<LoginCustUiState> get() = _loginCustUiState

    val buttonLoginEnabled
        get() = loginCustUiState.map {
            it.email.isNotEmpty() && it.email.isValidEmail() && it.password.length >= 6
        }

    val buttonLoginLoading
        get() = loginCustUiState.map {
            it.loginResult is Resource.Loading || it.loginResult is Resource.Success
        }

    fun updateEmail(email: String) {
        val emailErrorMessage = if (email.isEmpty()) {
            "Email harus diisi."
        } else if (!email.isValidEmail()) {
            "Email tidak valid."
        } else {
            ""
        }
        _loginCustUiState.update {
            it.copy(email = email, errorEmailMessage = emailErrorMessage)
        }
    }

    fun updatePassword(password: String) {
        val passwordErrorMessage = if (password.isEmpty()) {
            "Kata sandi harus diisi"
        } else if (password.length <= 6) {
            "Kata sandi kurang dari 7 karakter"
        } else {
            ""
        }

        _loginCustUiState.update {
            it.copy(password = password, errorPasswordMessage = passwordErrorMessage)
        }
    }

    fun login() {
        viewModelScope.launch {
            val email = _loginCustUiState.value.email
            val password = _loginCustUiState.value.password
            customerUseCase.login(email, password).collect { result ->
                _loginCustUiState.update {
                    it.copy(
                        loginResult = result
                    )
                }
            }
        }
    }
}