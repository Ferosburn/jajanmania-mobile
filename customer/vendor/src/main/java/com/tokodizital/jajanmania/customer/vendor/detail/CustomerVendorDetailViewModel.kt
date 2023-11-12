package com.tokodizital.jajanmania.customer.vendor.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerRefreshTokenResult
import com.tokodizital.jajanmania.core.domain.usecase.CustomerSessionUseCase
import com.tokodizital.jajanmania.core.domain.usecase.CustomerUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CustomerVendorDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val customerUseCase: CustomerUseCase,
    private val customerSessionUseCase: CustomerSessionUseCase
) : ViewModel() {

    private val _customerVendorDetailUiState = MutableStateFlow(CustomerVendorDetailUiState())
    val customerVendorDetailUiState: StateFlow<CustomerVendorDetailUiState> get() = _customerVendorDetailUiState
    val vendorId: String = checkNotNull(savedStateHandle["vendorId"])

    fun getVendorDetail(vendorId: String, token: String) {
        viewModelScope.launch {
            customerUseCase.getVendorDetail(vendorId, token).collect { result ->
                _customerVendorDetailUiState.update {
                    it.copy(
                        vendorDetailResult = result
                    )
                }
            }
        }
    }

    fun getCustomerSession() {
        viewModelScope.launch {
            customerSessionUseCase.customerSession
                .debounce(1000L)
                .collectLatest { result ->
                    _customerVendorDetailUiState.update {
                        it.copy(customerSession = result)
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
        firebaseToken: String,
    ) {
        viewModelScope.launch {
            customerUseCase.refreshToken(
                accountId = accountId,
                accountType = accountType,
                accessToken = accessToken,
                refreshToken = refreshToken,
                expiredAt = expiredAt,
                firebaseToken = firebaseToken
            ).collectLatest { result ->
                _customerVendorDetailUiState.update {
                    it.copy(refreshTokenResult = result)
                }
            }
        }
    }

    fun updateCustomerSession(refreshTokenResult: CustomerRefreshTokenResult) {
        viewModelScope.launch {
            customerSessionUseCase.updateCustomerSession(
                accountId = refreshTokenResult.accountId,
                accountType = refreshTokenResult.accountType,
                accessToken = refreshTokenResult.accessToken,
                refreshToken = refreshTokenResult.refreshToken,
                expiredAt = refreshTokenResult.expiredAt,
                firebaseToken = refreshTokenResult.firebaseToken,
            )
        }
    }

    fun deleteCustomerSession() {
        viewModelScope.launch {
            customerSessionUseCase.deleteCustomerSession()
        }
    }
}