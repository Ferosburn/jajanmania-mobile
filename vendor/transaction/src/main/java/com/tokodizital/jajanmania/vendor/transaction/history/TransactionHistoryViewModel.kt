package com.tokodizital.jajanmania.vendor.transaction.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.tokodizital.jajanmania.core.domain.model.vendor.RefreshTokenResult
import com.tokodizital.jajanmania.core.domain.model.vendor.VendorSession
import com.tokodizital.jajanmania.core.domain.model.vendor.transaction.TransactionHistoryItem
import com.tokodizital.jajanmania.core.domain.usecase.VendorPagedUseCase
import com.tokodizital.jajanmania.core.domain.usecase.VendorSessionUseCase
import com.tokodizital.jajanmania.core.domain.usecase.VendorUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TransactionHistoryViewModel(
    private val vendorUseCase: VendorUseCase,
    private val vendorSessionUseCase: VendorSessionUseCase,
    private val vendorPagedUseCase: VendorPagedUseCase
) : ViewModel() {

    private val _transactionHistoryUiState = MutableStateFlow(TransactionHistoryUiState())
    val transactionHistory: StateFlow<TransactionHistoryUiState> get() = _transactionHistoryUiState

    private val vendorSession = MutableStateFlow<VendorSession?>(null)

    val transactionHistoryPaged
        get() = vendorSession.filterNotNull()
            .distinctUntilChanged()
            .flatMapMerge { session ->
                vendorPagedUseCase.getTransactionHistory(
                    token = session.accessToken,
                    vendorId = session.accountId
                )
            }.cachedIn(viewModelScope)

    fun setVendorSession(vendorSession: VendorSession) {
        this.vendorSession.update { vendorSession }
    }

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

}