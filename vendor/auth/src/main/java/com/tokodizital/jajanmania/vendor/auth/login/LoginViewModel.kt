package com.tokodizital.jajanmania.vendor.auth.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {

    private val _loginUiState = MutableStateFlow(LoginUiState())
    val loginUiState: StateFlow<LoginUiState> get() = _loginUiState

    val buttonLoginEnabled get() = loginUiState.map {
        it.email.isNotEmpty() && it.password.length > 6
    }

    fun updateEmail(email: String) {
        _loginUiState.update {
            _loginUiState.value.copy(email = email)
        }
    }

    fun updatePassword(password: String) {
        _loginUiState.update {
            _loginUiState.value.copy(password = password)
        }
    }

    fun updateRememberMe(rememberMe: Boolean) {
        _loginUiState.update {
            _loginUiState.value.copy(rememberMe = rememberMe)
        }
    }

}