package com.tokodizital.jajanmania.customer.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerRefreshTokenResult
import com.tokodizital.jajanmania.core.domain.usecase.CustomerSessionUseCase
import com.tokodizital.jajanmania.core.domain.usecase.CustomerUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class ProfileViewModel(
    private val customerUseCase: CustomerUseCase,
    private val customerSessionUseCase: CustomerSessionUseCase,

): ViewModel()
{
    private val _profileUiState = MutableStateFlow(ProfileUiState())
    val profileUiState: StateFlow<ProfileUiState> get() = _profileUiState

    fun getCustomerSession(){
        viewModelScope.launch {
            customerSessionUseCase.customerSession
                .debounce(1000L)
                .distinctUntilChanged()
                .collectLatest { result ->
                    _profileUiState.update {
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
                _profileUiState.update {
                    it.copy(
                        customerRefreshToken = result
                    )
                }
            }
        }
    }

    fun getCustomer(token: String, id: String) {
        viewModelScope.launch {
            customerUseCase.getCustomer(token, id).collect { result ->
                delay(1000L)
                _profileUiState.update {
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

    fun logout() {
        viewModelScope.launch {
            val customerSession = _profileUiState.value.customerSession
            if (customerSession is Resource.Success) {
                val session = customerSession.data
                val accessToken = session.accessToken
                val refreshToken = session.refreshToken
                val accountType = session.accountType
                val accountId = session.accountId
                val expiredAt = session.expiredAt
                val firebaseToken = session.firebaseToken
                customerUseCase.logout(
                    accountId, accountType, accessToken, refreshToken, expiredAt, firebaseToken
                ).collect(){ result ->
                    _profileUiState.update {
                        it.copy(
                            customerLogoutResult = result
                        )
                    }
                }
            }
        }
    }
}