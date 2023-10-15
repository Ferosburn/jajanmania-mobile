package com.tokodizital.jajanmania.vendor.auth.register

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class RegisterViewModel : ViewModel() {

    private val _registerUiState = MutableStateFlow(RegisterUiState())
    val registerUiState: StateFlow<RegisterUiState> get() = _registerUiState

    val buttonRegisterEnabled get() = _registerUiState.map {
        it.shopName.isNotEmpty() &&
        it.ownerName.isNotEmpty() &&
        it.email.isNotEmpty() &&
        it.password.length > 6 &&
        it.confirmPassword == it.password &&
        it.userAgreement
    }

    fun updateShopName(shopName: String) {
        _registerUiState.update {
            _registerUiState.value.copy(shopName = shopName)
        }
    }

    fun updateOwnerName(ownerName: String) {
        _registerUiState.update {
            _registerUiState.value.copy(ownerName = ownerName)
        }
    }

    fun updateEmail(email: String) {
        _registerUiState.update {
            _registerUiState.value.copy(email = email)
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

    fun updateUserAgreement(userAgreement: Boolean) {
        _registerUiState.update {
            _registerUiState.value.copy(userAgreement = userAgreement)
        }
    }

}