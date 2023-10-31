package com.tokodizital.jajanmania.customer.auth.register

import androidx.lifecycle.ViewModel
import com.tokodizital.jajanmania.common.utils.isValidEmail
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
                it.gender != "Pilih jenis kelamin" &&
                it.username.isNotEmpty() &&
                it.password.length > 6 &&
                it.confirmPassword == it.password
    }

    fun updateFullname(fullname: String) {
        val fullnameErrorMessage = if (fullname.isEmpty()) {
            "Nama lengkap harus diisi"
        } else {
            ""
        }

        _registerCustUiState.update {
            it.copy(fullname = fullname, errorFullNameMessage = fullnameErrorMessage)
        }
    }

    fun updateEmail(email: String) {
        val emailErrorMessage = if ( email.isEmpty()) {
            "Email harus diisi."
        } else if (!email.isValidEmail()) {
            "Email tidak valid."
        } else {
            ""
        }
        _registerCustUiState.update {
            it.copy( email = email, errorEmailMessage = emailErrorMessage )
        }
    }

    fun updateGender(gender: String) {
        val genderErrorMessage = if (gender == "Pilih jenis kelamin") {
            "Pilih salah satu"
        } else {
            ""
        }

        _registerCustUiState.update {
            it.copy( gender = gender, errorGenderMessage = genderErrorMessage)
        }
    }

    fun updateUsername(username: String) {
        val usernameErrorMessage = if (username.isEmpty()) {
            "Nama pengguna harus diisi"
        } else {
            ""
        }

        _registerCustUiState.update {
            it.copy( username = username, errorUsernameMessage = usernameErrorMessage )
        }
    }

    fun updatePassword(password: String) {
        val confirmPassword = _registerCustUiState.value.confirmPassword

        val passwordErrorMessage = if (password.isEmpty()) {
            "Kata sandi harus diisi"
        } else if (password.length <= 6) {
            "Kata sandi kurang dari 7 karakter"
        } else if (password != confirmPassword) {
            "Kata sandi tidak sama"
        } else {
            ""
        }

        val confirmPasswordErrorMessage = if (confirmPassword.isEmpty()) {
            "Kata sandi harus diisi"
        } else if (confirmPassword.length <= 6) {
            "Kata sandi kurang dari 7 karakter"
        } else if (confirmPassword != password) {
            "Kata sandi tidak sama"
        } else {
            ""
        }

        _registerCustUiState.update {
            it.copy( password = password, errorPasswordMessage = passwordErrorMessage, errorConfirmPasswordMessage = confirmPasswordErrorMessage )
        }
    }

    fun updateConfirmPassword(confirmPassword: String) {
        val password = _registerCustUiState.value.password

        val confirmPasswordErrorMessage = if (confirmPassword.isEmpty()) {
            "Kata sandi harus diisi"
        } else if (confirmPassword.length <= 6) {
            "Kata sandi kurang dari 7 karakter"
        } else if (confirmPassword != password) {
            "Kata sandi tidak sama"
        } else {
            ""
        }

        val passwordErrorMessage = if (password.isEmpty()) {
            "Kata sandi harus diisi"
        } else if (password.length <= 6) {
            "Kata sandi kurang dari 7 karakter"
        } else if (password != confirmPassword) {
            "Kata sandi tidak sama"
        } else {
            ""
        }

        _registerCustUiState.update {
            it.copy( confirmPassword = confirmPassword, errorPasswordMessage = passwordErrorMessage, errorConfirmPasswordMessage = confirmPasswordErrorMessage )
        }
    }

}