package com.tokodizital.customer.topup

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
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CustomerTopUpViewModel(
    private val customerUseCase: CustomerUseCase,
    private val customerSessionUseCase: CustomerSessionUseCase
) : ViewModel() {

    private val _topUpUiState = MutableStateFlow(CustomerTopUpUiState())

    val customerTopUpUiState: StateFlow<CustomerTopUpUiState> get() = _topUpUiState

    fun topUp(
        token: String,
        userId: String,
        amount: String
    ) {
        viewModelScope.launch {
            customerUseCase.topUp(
                token = token,
                userId = userId,
                amount = amount
            ).collect { result ->
                _topUpUiState.update {
                    it.copy(
                        topUpResult = result,
                    )
                }

            }
        }
    }

    fun getCustomerSession(){
        viewModelScope.launch {
            customerSessionUseCase.customerSession
                .debounce(1000L)
                .distinctUntilChanged()
                .collectLatest { result ->
                    _topUpUiState.update {
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
        firebaseToken : String
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
                _topUpUiState.update {
                    it.copy(
                        refreshToken = result
                    )
                }
            }
        }
    }

    fun getCustomer(token: String, id: String) {
        viewModelScope.launch {
            customerUseCase.getCustomerAccount(token, id).collect { result ->
                delay(1000L)
                _topUpUiState.update {
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

    fun deleteCustomerSession() {
        viewModelScope.launch {
            customerSessionUseCase.deleteCustomerSession()
        }
    }

}