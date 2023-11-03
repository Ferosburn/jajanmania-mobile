package com.tokodizital.jajanmania.vendor.auth.register

import android.util.Patterns
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

class RegisterViewModel(
    private val vendorUseCase: VendorUseCase
) : ViewModel() {

    private val _registerUiState = MutableStateFlow(RegisterUiState())
    val registerUiState: StateFlow<RegisterUiState> get() = _registerUiState

    val buttonRegisterEnabled get() = _registerUiState.map {
        it.userName.isNotEmpty() &&
        it.fullName.isNotEmpty() &&
        it.email.isNotEmpty() &&
        it.email.isValidEmail() &&
        it.password.length >= 6 &&
        it.confirmPassword == it.password
    }

    val buttonRegisterLoading get() = _registerUiState.map {
        it.registerResult is Resource.Loading || it.registerResult is Resource.Success
    }

    fun updateFullNameName(fullName: String) {
        _registerUiState.update {
            _registerUiState.value.copy(fullName = fullName)
        }
    }

    fun updateUserName(userName: String) {
        _registerUiState.update {
            _registerUiState.value.copy(userName = userName)
        }
    }

    fun updateEmail(email: String) {
        _registerUiState.update {
            it.copy(email = email)
        }
    }

    fun updateGender(gender: String) {
        _registerUiState.update {
            it.copy(gender = gender)
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

    fun updateErrorFullNameMessage(message: String) {
        _registerUiState.update {
            _registerUiState.value.copy(errorFullNameMessage = message)
        }
    }

    fun updateErrorUserNameMessage(message: String) {
        _registerUiState.update {
            _registerUiState.value.copy(errorUserNameMessage = message)
        }
    }

    fun updateErrorEmailMessage(message: String) {
        _registerUiState.update {
            _registerUiState.value.copy(errorEmailMessage = message)
        }
    }

    fun updateErrorPasswordMessage(message: String) {
        _registerUiState.update {
            _registerUiState.value.copy(errorPasswordMessage = message)
        }
    }

    fun updateErrorConfirmPasswordMessage(message: String) {
        _registerUiState.update {
            _registerUiState.value.copy(errorConfirmPasswordMessage = message)
        }
    }

    fun register() {
        viewModelScope.launch {
            val fullName = registerUiState.value.fullName
            val username = registerUiState.value.fullName
            val email = registerUiState.value.fullName
            val gender = registerUiState.value.fullName
            val password = registerUiState.value.fullName
            vendorUseCase.register(
                fullName = fullName,
                username = username,
                email = email,
                gender = gender,
                password = password
            ).collect { result ->
                _registerUiState.update {
                    it.copy(
                        registerResult = result
                    )
                }
            }
        }
    }

}