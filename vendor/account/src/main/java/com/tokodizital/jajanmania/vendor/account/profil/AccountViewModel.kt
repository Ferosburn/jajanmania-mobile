package com.tokodizital.jajanmania.vendor.account.profil

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.vendor.RefreshTokenResult
import com.tokodizital.jajanmania.core.domain.usecase.VendorSessionUseCase
import com.tokodizital.jajanmania.core.domain.usecase.VendorUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AccountViewModel(
    private val vendorUseCase: VendorUseCase,
    private val vendorSessionUseCase: VendorSessionUseCase
) : ViewModel() {

    private val _accountUiState = MutableStateFlow(AccountUiState())
    val accountUiState: StateFlow<AccountUiState> get() = _accountUiState

    fun getVendorSession() {
        viewModelScope.launch {
            vendorSessionUseCase.vendorSession
                .debounce(1000L)
                .distinctUntilChanged()
                .collectLatest { result ->
                    _accountUiState.update {
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
                _accountUiState.update {
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
                _accountUiState.update {
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

    fun logout() {
        viewModelScope.launch {
            val vendorSession = _accountUiState.value.vendorSession
            if (vendorSession is Resource.Success) {
                val session = vendorSession.data
                val accessToken = session.accessToken
                val refreshToken = session.refreshToken
                val accountType = session.accountType
                val accountId = session.accountId
                val expiredAt = session.expiredAt
                vendorUseCase.logout(
                    accountId, accountType, accessToken, refreshToken, expiredAt
                ).collect { result ->
                    _accountUiState.update {
                        it.copy(
                            logoutResult = result
                        )
                    }
                }
            }
        }
    }
    
}