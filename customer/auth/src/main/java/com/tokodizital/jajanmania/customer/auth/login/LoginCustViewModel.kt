package com.tokodizital.jajanmania.customer.auth.login

import androidx.lifecycle.ViewModel
import com.tokodizital.jajanmania.common.utils.isValidEmail
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
        val emailErrorMessage = if ( email.isEmpty()) {
            "Email harus diisi."
        } else if (!email.isValidEmail()) {
            "Email tidak valid."
        } else {
            ""
        }
        _loginCustUiState.update {
            it.copy( email = email, errorEmailMessage = emailErrorMessage )
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
            it.copy( password = password, errorPasswordMessage = passwordErrorMessage )
        }
    }
}