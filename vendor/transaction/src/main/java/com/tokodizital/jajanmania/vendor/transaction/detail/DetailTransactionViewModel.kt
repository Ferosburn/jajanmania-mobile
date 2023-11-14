package com.tokodizital.jajanmania.vendor.transaction.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tokodizital.jajanmania.core.domain.model.vendor.RefreshTokenResult
import com.tokodizital.jajanmania.core.domain.usecase.VendorSessionUseCase
import com.tokodizital.jajanmania.core.domain.usecase.VendorUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailTransactionViewModel(
    private val vendorUseCase: VendorUseCase,
    private val vendorSessionUseCase: VendorSessionUseCase,
) : ViewModel() {

    private val _detailTransactionUiState = MutableStateFlow(DetailTransactionUiState())
    val detailTransactionUiState: StateFlow<DetailTransactionUiState> get() = _detailTransactionUiState

    fun getVendorSession() {
        viewModelScope.launch {
            vendorSessionUseCase.vendorSession
                .debounce(1000L)
                .collectLatest { result ->
                    _detailTransactionUiState.update {
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
                _detailTransactionUiState.update {
                    it.copy(
                        refreshToken = result
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

    fun getDetailTransaction(
        token: String,
        transactionId: String
    ) {
        viewModelScope.launch {
            vendorUseCase.getDetailTransaction(token, transactionId).collect { result ->
                _detailTransactionUiState.update {
                    it.copy(
                        transactionHistory = result
                    )
                }
            }
        }
    }

}