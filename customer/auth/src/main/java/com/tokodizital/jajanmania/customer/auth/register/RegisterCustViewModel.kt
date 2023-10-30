package com.tokodizital.jajanmania.customer.auth.register

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class RegisterCustViewModel : ViewModel() {

    private val _registerCustUiState = MutableStateFlow(RegisterCustUiState())

    val registerCustUiState: StateFlow<RegisterCustUiState> get() = _registerCustUiState

    val buttonRegisterEnabled get() = _registerCustUiState.map {
        it.fullname.isNotEmpty() &&
                it.email.isNotEmpty() &&
                it.username.isNotEmpty() &&
                it.password.length > 6 &&
                it.confirmPassword == it.password
    }

    fun updateFullname(fullname: String) {
        _registerCustUiState.update {
            _registerCustUiState.value.copy(fullname = fullname)
        }
    }

    fun updateEmail(email: String) {
        _registerCustUiState.update {
            _registerCustUiState.value.copy(email = email)
        }
    }

    fun updateUsername(username: String) {
        _registerCustUiState.update {
            _registerCustUiState.value.copy(username = username)
        }
    }

    fun updatePassword(password: String) {
        _registerCustUiState.update {
            _registerCustUiState.value.copy(password = password)
        }
    }

    fun updateConfirmPassword(confirmPassword: String) {
        _registerCustUiState.update {
            _registerCustUiState.value.copy(confirmPassword = confirmPassword)
        }
    }

}