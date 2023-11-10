package com.tokodizital.jajanmania.vendor.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tokodizital.jajanmania.core.domain.model.vendor.RefreshTokenResult
import com.tokodizital.jajanmania.core.domain.usecase.VendorSessionUseCase
import com.tokodizital.jajanmania.core.domain.usecase.VendorUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val vendorUseCase: VendorUseCase,
    private val vendorSessionUseCase: VendorSessionUseCase
) : ViewModel() {

    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState: StateFlow<HomeUiState> get() = _homeUiState

    fun getVendorSession() {
        viewModelScope.launch {
            vendorSessionUseCase.vendorSession
                .debounce(1000L)
                .collectLatest { result ->
                _homeUiState.update {
                    it.copy(
                        vendorSession = result
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
                _homeUiState.update {
                    it.copy(
                        refreshToken = result
                    )
                }
            }
        }
    }

    fun getVendor(token: String, id: String) {
        viewModelScope.launch {
            vendorUseCase.getVendor(token, id).collect { result ->
                delay(1000L)
                _homeUiState.update {
                    it.copy(
                        vendor = result
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

    fun getTransactionHistories(
        token: String, vendorId: String
    ) {
        viewModelScope.launch {
            vendorUseCase.getTransactionHistory(
                token = token, vendorId = vendorId
            ).collect { result ->
                delay(1000L)
                _homeUiState.update {
                    it.copy(
                        transactionHistory = result
                    )
                }
            }
        }
    }

}