package com.tokodizital.jajanmania.customer.auth.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class LoginCustViewModel : ViewModel() {

    private val _loginCustUiState = MutableStateFlow(LoginCustUiState())
    val loginCustUiState: StateFlow<LoginCustUiState> get() = _loginCustUiState

    val buttonLoginEnabled get() = loginCustUiState.map {
        it.email.isNotEmpty() && it.password.length > 6
    }

    fun updateEmail(email: String) {
        _loginCustUiState.update {
            _loginCustUiState.value.copy(email = email)
        }
    }

    fun updatePassword(password: String) {
        _loginCustUiState.update {
            _loginCustUiState.value.copy(password = password)
        }
    }
}