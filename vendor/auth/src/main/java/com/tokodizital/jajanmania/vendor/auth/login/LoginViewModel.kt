package com.tokodizital.jajanmania.vendor.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tokodizital.jajanmania.common.utils.isValidEmail
import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.usecase.VendorUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val vendorUseCase: VendorUseCase
) : ViewModel() {

    private val _loginUiState = MutableStateFlow(LoginUiState())
    val loginUiState: StateFlow<LoginUiState> get() = _loginUiState

    val buttonLoginEnabled get() = loginUiState.map {
        it.email.isNotEmpty() && it.password.length >= 6 && it.email.isValidEmail()
    }

    val buttonLoginLoading get() = loginUiState.map {
        it.loginResult is Resource.Loading || it.loginResult is Resource.Success
    }

    fun updateEmail(email: String) {
        val errorMessage = if (!email.isValidEmail()) {
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

    fun updateEmailErrorMessage(message: String) {
        _loginUiState.update {
            _loginUiState.value.copy(errorEmailMessage = message)
        }
    }

    fun updatePasswordErrorMessage(message: String) {
        _loginUiState.update {
            _loginUiState.value.copy(errorPasswordMessage = message)
        }
    }


    fun login() {
        viewModelScope.launch {
            val email = _loginUiState.value.email
            val password = _loginUiState.value.password
            vendorUseCase.login(email, password).collect { result ->
                _loginUiState.update {
                    it.copy(
                        loginResult = result
                    )
                }
            }
        }

    }
}