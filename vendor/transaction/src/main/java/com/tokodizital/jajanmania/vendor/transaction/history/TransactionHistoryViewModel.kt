package com.tokodizital.jajanmania.vendor.transaction.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.tokodizital.jajanmania.core.domain.model.vendor.RefreshTokenResult
import com.tokodizital.jajanmania.core.domain.model.vendor.transaction.TransactionHistoryItem
import com.tokodizital.jajanmania.core.domain.usecase.VendorPagedUseCase
import com.tokodizital.jajanmania.core.domain.usecase.VendorSessionUseCase
import com.tokodizital.jajanmania.core.domain.usecase.VendorUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TransactionHistoryViewModel(
    private val vendorUseCase: VendorUseCase,
    private val vendorSessionUseCase: VendorSessionUseCase,
    private val vendorPagedUseCase: VendorPagedUseCase
) : ViewModel() {

    private val _transactionHistoryUiState = MutableStateFlow(TransactionHistoryUiState())
    val transactionHistory: StateFlow<TransactionHistoryUiState> get() = _transactionHistoryUiState

    private val _transactionHistoryPaged = MutableStateFlow<PagingData<TransactionHistoryItem>>(PagingData.empty())
    val transactionHistoryPaged: StateFlow<PagingData<TransactionHistoryItem>> get() = _transactionHistoryPaged

    fun getVendorSession() {
        viewModelScope.launch {
            vendorSessionUseCase.vendorSession
                .debounce(1000L)
                .collectLatest { result ->
                _transactionHistoryUiState.update {
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
                _transactionHistoryUiState.update {
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

    fun getTransactionHistories(
        token: String, vendorId: String
    ) {
        vendorPagedUseCase
    }

}