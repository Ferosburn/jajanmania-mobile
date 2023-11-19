package com.tokodizital.jajanmania.vendor.shop.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.vendor.LoginResult
import com.tokodizital.jajanmania.core.domain.model.vendor.VendorSession
import com.tokodizital.jajanmania.core.domain.usecase.VendorPagedUseCase
import com.tokodizital.jajanmania.core.domain.usecase.VendorSessionUseCase
import com.tokodizital.jajanmania.core.domain.usecase.VendorUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ShopViewModel(
    private val vendorUseCase: VendorUseCase,
    private val vendorSessionUseCase: VendorSessionUseCase,
    private val vendorPagedUseCase: VendorPagedUseCase
) : ViewModel() {
    private val _shopUiState = MutableStateFlow(ShopUiState())
    val shopUiState: StateFlow<ShopUiState> get() = _shopUiState

    private val vendorSession = MutableStateFlow<VendorSession?>(null)

    val listJajanPaged
        get() = vendorSession.filterNotNull()
            .distinctUntilChanged()
            .flatMapMerge { session ->
                vendorPagedUseCase.listJajan(
                    token = session.accessToken,
                    vendorId = session.accountId
                )
            }.cachedIn(viewModelScope)

    fun updateToken(token: String) {
        _shopUiState.update {
            _shopUiState.value.copy(token = token)
        }
    }

    fun updateId(id: String) {
        _shopUiState.update {
            _shopUiState.value.copy(id = id)
        }
    }

    fun updateVendorSession(loginResult: LoginResult) {
        viewModelScope.launch {
            vendorSessionUseCase.updateVendorSession(
                accountId = loginResult.accountId ?: "",
                accountType = loginResult.accountType ?: "",
                accessToken = loginResult.accessToken ?: "",
                refreshToken = loginResult.refreshToken ?: "",
                expiredAt = loginResult.expiredAt ?: ""
            )
        }
    }

    fun getVendorSession() {
        viewModelScope.launch {
            Log.e("TAG", "getVendorSession: test", )
            vendorSessionUseCase.vendorSession
                .debounce(1000L)
                .distinctUntilChanged()
                .collectLatest { result ->
                    _shopUiState.update {
                        it.copy(
                            vendorSession = result
                        )
                    }
                }
        }
    }

    fun setVendorSession(vendorSession: VendorSession) {
        this.vendorSession.update { vendorSession }
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
                _shopUiState.update {
                    it.copy(
                        refreshToken = result
                    )
                }
            }
        }
    }
}