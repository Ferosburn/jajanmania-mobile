package com.tokodizital.jajanmania.customer.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tokodizital.jajanmania.common.data.Gender
import com.tokodizital.jajanmania.common.utils.isValidEmail
import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.usecase.CustomerUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterCustViewModel(
    private val customerUseCase: CustomerUseCase
) : ViewModel() {

    private val _registerCustUiState = MutableStateFlow(RegisterCustUiState())
    val registerCustUiState: StateFlow<RegisterCustUiState> get() = _registerCustUiState

    val buttonRegisterEnabled
        get() = _registerCustUiState.map {
            it.fullName.isNotEmpty() &&
                    it.username.isNotEmpty() &&
                    it.email.isNotEmpty() &&
                    it.email.isValidEmail() &&
                    it.password.length >= 6 &&
                    it.confirmPassword == it.password
        }

    val buttonRegisterLoading
        get() = _registerCustUiState.map {
            it.registerResult is Resource.Loading || it.registerResult is Resource.Success
        }

    fun updateFullName(fullName: String) {
        _registerCustUiState.update {
            it.copy(fullName = fullName)
        }
    }

    fun updateUsername(username: String) {
        _registerCustUiState.update {
            it.copy(username = username)
        }
    }

    fun updateEmail(email: String) {
        _registerCustUiState.update {
            it.copy(email = email)
        }
    }

    fun updateGender(gender: String) {
        _registerCustUiState.update {
            it.copy( gender = gender)
        }
    }

    fun updatePassword(password: String) {
        _registerCustUiState.update {
            it.copy(password = password)
        }
    }

    fun updateConfirmPassword(confirmPassword: String) {
        _registerCustUiState.update {
            it.copy(
                confirmPassword = confirmPassword,
            )
        }
    }

    fun updateErrorFullNameMessage(message: String) {
        _registerCustUiState.update {
            _registerCustUiState.value.copy(errorFullNameMessage = message)
        }
    }

    fun updateErrorUsernameMessage(message: String) {
        _registerCustUiState.update {
            _registerCustUiState.value.copy(errorUsernameMessage = message)
        }
    }

    fun updateErrorEmailMessage(message: String) {
        _registerCustUiState.update {
            _registerCustUiState.value.copy(errorEmailMessage = message)
        }
    }

    private fun updateErrorGenderMessage(message: String) {
        _registerCustUiState.update {
            _registerCustUiState.value.copy(errorGenderMessage = message)
        }
    }

    fun updateErrorPasswordMessage(message: String) {
        _registerCustUiState.update {
            _registerCustUiState.value.copy(errorPasswordMessage = message)
        }
    }

    fun updateErrorConfirmPasswordMessage(message: String) {
        _registerCustUiState.update {
            _registerCustUiState.value.copy(errorConfirmPasswordMessage = message)
        }
    }

    fun register() {
        if (registerCustUiState.value.gender.isNotEmpty()) {
            updateErrorGenderMessage("")
            viewModelScope.launch {
                val genderDisplayName = registerCustUiState.value.gender
                val selectedGender = Gender.values().first { it.displayName == genderDisplayName }

                val fullName = registerCustUiState.value.fullName
                val username = registerCustUiState.value.username
                val email = registerCustUiState.value.email
                val gender = selectedGender.name
                val password = registerCustUiState.value.password

                customerUseCase.register(
                    fullName = fullName,
                    username = username,
                    email = email,
                    gender = gender,
                    password = password,
                ).collect {result ->
                    _registerCustUiState.update {
                        it.copy(
                            registerResult = result
                        )
                    }
                }
            }
        } else {
            updateErrorGenderMessage("Pilih salah satu!")
        }
    }
}