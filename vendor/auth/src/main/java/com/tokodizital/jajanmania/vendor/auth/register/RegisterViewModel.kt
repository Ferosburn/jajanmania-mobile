package com.tokodizital.jajanmania.vendor.auth.register

import android.util.Patterns
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class RegisterViewModel : ViewModel() {

    private val _registerUiState = MutableStateFlow(RegisterUiState())
    val registerUiState: StateFlow<RegisterUiState> get() = _registerUiState

    val buttonRegisterEnabled get() = _registerUiState.map {
        it.userName.isNotEmpty() &&
        it.ownerName.isNotEmpty() &&
        it.email.isNotEmpty() &&
        it.password.length > 6 &&
        it.confirmPassword == it.password &&
        isValidEmail(it.email)
    }

    fun updateUserName(userName: String) {
        _registerUiState.update {
            _registerUiState.value.copy(userName = userName)
        }
    }

    fun updateOwnerName(ownerName: String) {
        _registerUiState.update {
            _registerUiState.value.copy(ownerName = ownerName)
        }
    }

    fun updateEmail(email: String) {
        val errorMessage = if (!isValidEmail(email)) {
            "Email tidak valid"
        } else {
            ""
        }
        _registerUiState.update {
            it.copy(email = email, errorEmailMessage = errorMessage)
        }
    }

    fun updatePassword(password: String) {
        _registerUiState.update {
            _registerUiState.value.copy(password = password)
        }
    }

    fun updateConfirmPassword(confirmPassword: String) {
        _registerUiState.update {
            _registerUiState.value.copy(confirmPassword = confirmPassword)
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}