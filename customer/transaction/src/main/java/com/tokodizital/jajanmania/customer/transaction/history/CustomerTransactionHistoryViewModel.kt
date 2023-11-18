package com.tokodizital.jajanmania.customer.transaction.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerRefreshTokenResult
import com.tokodizital.jajanmania.core.domain.usecase.CustomerSessionUseCase
import com.tokodizital.jajanmania.core.domain.usecase.CustomerUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CustomerTransactionHistoryViewModel(
    private val customerUseCase: CustomerUseCase,
    private val customerSessionUseCase: CustomerSessionUseCase
) : ViewModel() {

    private val _customerTransactionHistoryUiState =
        MutableStateFlow(CustomerTransactionHistoryUiState())
    val customerTransactionHistoryUiState: StateFlow<CustomerTransactionHistoryUiState> get() = _customerTransactionHistoryUiState
    val loadMoreButtonIsLoading get() = customerTransactionHistoryUiState.map { it.transactionHistory is Resource.Loading }

    fun getTransactionHistory(
        token: String,
        userId: String,
        pageNumber: Int = 1,
    ) {
        viewModelScope.launch {
            customerUseCase.getTransactionHistory(
                token = token,
                userId = userId,
                pageNumber = pageNumber,
            ).collect { result ->
                _customerTransactionHistoryUiState.update {
                    val items = if (result is Resource.Success) result.data else listOf()
                    it.copy(transactionHistory = result, transactionHistoryList = items, pageNumber = pageNumber)
                }
            }
        }
    }

    fun loadMore(
        token: String,
        userId: String,
    ) {
        viewModelScope.launch {
            val pageNumber = customerTransactionHistoryUiState.value.pageNumber + 1
            customerUseCase.getTransactionHistory(
                token = token,
                userId = userId,
                pageNumber = pageNumber,
            ).collect { result ->
                _customerTransactionHistoryUiState.update {
                    val items = it.transactionHistoryList + if (result is Resource.Success) result.data else listOf()
                    it.copy(transactionHistory = result, transactionHistoryList = items, pageNumber = pageNumber)
                }
            }
        }
    }

    fun getCustomerSession() {
        viewModelScope.launch {
            customerSessionUseCase.customerSession
                .debounce(1000L)
                .collectLatest { result ->
                    _customerTransactionHistoryUiState.update {
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
                _customerTransactionHistoryUiState.update {
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