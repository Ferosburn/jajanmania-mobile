package com.tokodizital.jajanmania.vendor.auth.login

import android.util.Patterns
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {

    private val _loginUiState = MutableStateFlow(LoginUiState())
    val loginUiState: StateFlow<LoginUiState> get() = _loginUiState

    val buttonLoginEnabled get() = loginUiState.map {
        it.email.isNotEmpty() && it.password.length > 6 && isValidEmail(it.email)
    }

    fun updateEmail(email: String) {
        val errorMessage = if (!isValidEmail(email)) {
            "Email tidak valid"
        } else {
            ""
        }
        _loginUiState.update {
            it.copy(email = email, errorEmailMessage = errorMessage)
        }
    }

    fun updatePassword(password: String) {
        _loginUiState.update {
            _loginUiState.value.copy(password = password)
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

}