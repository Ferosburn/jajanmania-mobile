package com.tokodizital.jajanmania.customer.profile.manage


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tokodizital.jajanmania.common.data.Gender
import com.tokodizital.jajanmania.common.utils.isValidEmail
import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerRefreshTokenResult
import com.tokodizital.jajanmania.core.domain.usecase.CustomerSessionUseCase
import com.tokodizital.jajanmania.core.domain.usecase.CustomerUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ManageProfileViewModel(
    private val customerUseCase: CustomerUseCase,
    private val customerSessionUseCase: CustomerSessionUseCase
) : ViewModel() {

    private val _manageProfileUiState = MutableStateFlow(ManageProfileUiState())
    val manageProfileUiState: StateFlow<ManageProfileUiState> get() = _manageProfileUiState

    private var _accountId = ""
    private var _accessToken = ""

    val buttonUpdateEnabled
        get() = _manageProfileUiState.map {
            it.fullName.isNotEmpty() &&
                    it.address.isNotEmpty() &&
                    it.email.isNotEmpty() && it.email.isValidEmail() &&
                    it.gender.isNotEmpty() &&
                    it.oldPassword.length >= 6 &&
                    it.newPassword.length >= 6 &&
                    it.newPassword == it.newPasswordConfirm
        }

    val buttonUpdateLoaging
        get() = _manageProfileUiState.map {
            it.customer is Resource.Loading
        }


    fun getCustomerSession() {
        viewModelScope.launch {
            customerSessionUseCase.customerSession
                .debounce(1000L)
                .collectLatest { result ->
                    _manageProfileUiState.update {
                        it.copy(
                            customerSession = result
                        )
                    }
                }
        }
    }

    fun refreshToken(
        accountId: String,
        accountType: String,
        accessToken: String,
        refreshToken: String,
        expiredAt: String,
        firebaseToken: String
    ) {
        viewModelScope.launch {
            customerUseCase.refreshToken(
                accountId,
                accountType,
                accessToken,
                refreshToken,
                expiredAt,
                firebaseToken
            ).collect { result ->
                _manageProfileUiState.update {
                    it.copy(
                        refreshToken = result
                    )
                }
            }
        }
    }

    fun getCustomer(token: String, id: String) {
        viewModelScope.launch {
            customerUseCase.getCustomer(token, id).collect { result ->
                delay(1000L)
                _manageProfileUiState.update {
                    it.copy(
                        customer = result
                    )
                }
            }
        }
    }

    fun updateCustomer(
        fullName: String,
        email: String,
        address: String,
        gender: String,
        oldPassword: String,
        newPassword: String
    ) {
        viewModelScope.launch {
            customerUseCase.updateCustomer(
                token = _accessToken,
                id = _accountId,
                fullName = fullName,
                email = email,
                address = address,
                gender = gender,
                oldPassword = oldPassword,
                newPassword = newPassword
            ).collect { result ->
                delay(1000L)
                _manageProfileUiState.update {
                    it.copy(
                        customerUpdateResult = result
                    )
                }
            }
        }
    }

    fun updateCustomerSession(refreshToken: CustomerRefreshTokenResult) {
        viewModelScope.launch {
            customerSessionUseCase.updateCustomerSession(
                accountId = refreshToken.accountId,
                accountType = refreshToken.accountType,
                accessToken = refreshToken.accessToken,
                refreshToken = refreshToken.refreshToken,
                expiredAt = refreshToken.expiredAt,
                firebaseToken = refreshToken.firebaseToken
            )
        }
    }


    fun updateFullName(value: String) {
        _manageProfileUiState.update {
            _manageProfileUiState.value.copy(fullName = value)
        }
    }
    fun updateEmail(value: String) {
        _manageProfileUiState.update {
            _manageProfileUiState.value.copy(email = value)
        }
    }
    fun updateAddress(value: String) {
        _manageProfileUiState.update {
            _manageProfileUiState.value.copy(address = value)
        }
    }
    fun updateOldPassword(value: String) {
        _manageProfileUiState.update {
            _manageProfileUiState.value.copy(oldPassword = value)
        }
    }
    fun updateNewPassword(value: String) {
        _manageProfileUiState.update {
            _manageProfileUiState.value.copy(newPassword = value)
        }
    }
    fun updateNewPasswordConfirm(value: String) {
        _manageProfileUiState.update {
            _manageProfileUiState.value.copy(newPasswordConfirm = value)
        }
    }
    fun updateGender(value: String) {
        val genderCode = if (value == Gender.MALE.displayName) {
            Gender.MALE.name
        } else {
            Gender.FEMALE.name
        }
        _manageProfileUiState.update {
            _manageProfileUiState.value.copy(gender = value, genderCode = genderCode)
        }
    }
    fun updateErrorFullNameMessage(message: String) {
        _manageProfileUiState.update {
            _manageProfileUiState.value.copy(errorFullNameMessage = message)
        }
    }
    fun updateErrorEmailMessage(message: String) {
        _manageProfileUiState.update {
            _manageProfileUiState.value.copy(errorEmailMessage = message)
        }
    }
    fun updateErrorAddressMessage(message: String) {
        _manageProfileUiState.update {
            _manageProfileUiState.value.copy(errorAddressMessage = message)
        }
    }
    fun updateErrorOldPasswordMessage(message: String) {
        _manageProfileUiState.update {
            _manageProfileUiState.value.copy(errorOldPasswordMessage = message)
        }
    }
    fun updateErrorNewPasswordMessage(message: String) {
        _manageProfileUiState.update {
            _manageProfileUiState.value.copy(errorNewPasswordMessage = message)
        }
    }
    fun updateErrorNewPasswordConfirmMessage(message: String) {
        _manageProfileUiState.update {
            _manageProfileUiState.value.copy(errorNewPasswordConfirmMessage = message)
        }
    }

    fun saveToken(token: String) {
        _accessToken = token
    }

    fun saveId(id: String) {
        _accountId = id
    }


}