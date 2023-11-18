package com.tokodizital.jajanmania.customer.auth.splashscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerRefreshTokenResult
import com.tokodizital.jajanmania.core.domain.model.vendor.RefreshTokenResult
import com.tokodizital.jajanmania.core.domain.usecase.CustomerSessionUseCase
import com.tokodizital.jajanmania.core.domain.usecase.CustomerUseCase
import com.tokodizital.jajanmania.core.domain.usecase.VendorSessionUseCase
import com.tokodizital.jajanmania.core.domain.usecase.VendorUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SplashScreenViewModel(
    private val customerSessionUseCase: CustomerSessionUseCase,
    private val customerUseCase: CustomerUseCase,
    private val vendorSessionUseCase: VendorSessionUseCase,
    private val vendorUseCase: VendorUseCase
) : ViewModel() {

    private val _splashScreenUiState = MutableStateFlow(SplashScreenUiState())
    val splashScreenUiState: StateFlow<SplashScreenUiState> get() = _splashScreenUiState

    fun getCustomerSession() {
        viewModelScope.launch {
            customerSessionUseCase.customerSession
                .debounce(1000L)
                .distinctUntilChanged()
                .collectLatest { result ->
                    _splashScreenUiState.update {
                        it.copy(customerSession = result)
                    }
                }
        }
    }

    fun customerRefreshToken(
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
                _splashScreenUiState.update {
                    it.copy(customerRefreshTokenResult = result)
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

    fun getVendorSession() {
        viewModelScope.launch {
            vendorSessionUseCase.vendorSession
                .debounce(1000L)
                .distinctUntilChanged()
                .collectLatest { result ->
                    _splashScreenUiState.update {
                        it.copy(
                            vendorSession = result
                        )
                    }
                }
        }
    }

    fun vendorRefreshToken(
        accountId: String,
        accountType: String,
        accessToken: String,
        refreshToken: String,
        expiredAt: String
    ) {
        viewModelScope.launch {
            vendorUseCase.refreshToken(
                accountId,
                accountType,
                accessToken,
                refreshToken,
                expiredAt
            ).collect { result ->
                _splashScreenUiState.update {
                    it.copy(
                        vendorRefreshTokenResult = result
                    )
                }
            }
        }
    }

    fun updateVendorSession(refreshToken: RefreshTokenResult) {
        viewModelScope.launch {
            vendorSessionUseCase.updateVendorSession(
                accountId = refreshToken.accountId,
                accountType = refreshToken.accountType,
                accessToken = refreshToken.accessToken,
                refreshToken = refreshToken.refreshToken,
                expiredAt = refreshToken.expiredAt
            )
        }
    }
}