package com.tokodizital.jajanmania.customer.profile.manage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerRefreshTokenResult
import com.tokodizital.jajanmania.core.domain.usecase.CustomerSessionUseCase
import com.tokodizital.jajanmania.core.domain.usecase.CustomerUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ManageProfileViewModel(
    private val customerUseCase: CustomerUseCase,
    private val customerSessionUseCase: CustomerSessionUseCase
) : ViewModel() {

    private val _manageProfileUiState = MutableStateFlow(ManageProfileUiState())
    val manageProfileUiState: StateFlow<ManageProfileUiState> get() = _manageProfileUiState

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
    fun updateGender(value: String) {
        _manageProfileUiState.update {
            _manageProfileUiState.value.copy(gender = value)
        }
    }
    fun updateErrorFullNameMessage(message: String) {
        _manageProfileUiState.update {
            _manageProfileUiState.value.copy(errorFullNameMessage = message)
        }
    }
    fun updateErrorAddressMessage(message: String) {
        _manageProfileUiState.update {
            _manageProfileUiState.value.copy(errorAddressMessage = message)
        }
    }
}