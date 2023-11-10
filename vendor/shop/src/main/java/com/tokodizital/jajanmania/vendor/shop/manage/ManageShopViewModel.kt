package com.tokodizital.jajanmania.vendor.shop.manage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.usecase.VendorSessionUseCase
import com.tokodizital.jajanmania.core.domain.usecase.VendorUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ManageShopViewModel(
    private val vendorUseCase: VendorUseCase,
    private val vendorSessionUseCase: VendorSessionUseCase
) : ViewModel() {

    private val _manageShopUiState = MutableStateFlow(ManageShopUiState())
    val manageShopUiState: StateFlow<ManageShopUiState> get() = _manageShopUiState

    val switchManageShopEnable = _manageShopUiState.map {
        it.isShopActive is Resource.Success
    }

    fun getVendorSession() {
        viewModelScope.launch {
            vendorSessionUseCase.vendorSession
                .debounce(1000L)
                .collectLatest { result ->
                    _manageShopUiState.update {
                        it.copy(
                            vendorSession = result
                        )
                    }
                }
        }
    }

    fun getShopStatus(
        token: String,
        id: String
    ) {
        viewModelScope.launch {
            vendorUseCase.getShopStatus(token, id).collect { result ->
                _manageShopUiState.update {
                    it.copy(
                        isShopActive = result
                    )
                }
            }
        }
    }

}