package com.tokodizital.jajanmania.customer.cart.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tokodizital.jajanmania.core.domain.model.customer.CartJajanItem
import com.tokodizital.jajanmania.core.domain.usecase.CustomerSessionUseCase
import com.tokodizital.jajanmania.core.domain.usecase.CustomerUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CheckoutViewModel(
    savedStateHandle: SavedStateHandle,
    private val customerUseCase: CustomerUseCase,
    private val customerSessionUseCase: CustomerSessionUseCase
) : ViewModel() {

    val vendorId: String = checkNotNull(savedStateHandle["vendorId"])

    private val _checkoutUiState = MutableStateFlow(CheckoutUiState())
    val checkoutUiState: StateFlow<CheckoutUiState> get() = _checkoutUiState

    fun getCustomerSession() {
        viewModelScope.launch {
            customerSessionUseCase.customerSession
                .debounce(1000L)
                .collectLatest { result ->
                    _checkoutUiState.update {
                        it.copy(
                            customerSession = result
                        )
                    }
                }
        }
    }
//    fun getVendorId(): String {
//        return vendorId
//    }
    fun getVendorDetail(vendorId: String, token: String) {
        viewModelScope.launch {
            customerUseCase.getVendorDetail(vendorId, token).collect { result ->
                _checkoutUiState.update {
                    it.copy(
                        vendorDetailResult = result
                    )
                }
            }
        }
    }

    fun getJajanItems(
        token: String,
        vendorId: String,
        pageNumber: Int,
        pageSize: Int
    ) {
        viewModelScope.launch {
            customerUseCase.getJajanItems(
                token = token,
                vendorId = vendorId,
                pageNumber = pageNumber,
                pageSize = pageSize
            )
                .debounce(1000L)
                .collectLatest{ result ->
//                delay(1000L)
                _checkoutUiState.update {
                    it.copy(
                        vendorJajanItemResource = result
                    )
                }
            }
        }
    }

    fun updateVendorId(value: String) {
        _checkoutUiState.update {
            _checkoutUiState.value.copy(vendorId = value)
        }
    }

    fun updateButtonProceed(value: Boolean) {
        _checkoutUiState.update {
            _checkoutUiState.value.copy(buttonProceedEnabled = value)
        }
    }

    fun updateCustomerAccessToken(value: String) {
        _checkoutUiState.update {
            _checkoutUiState.value.copy(customerAccessToken = value)
        }
    }

    fun updateVendorJajanItem(value: List<CartJajanItem>) {
        _checkoutUiState.update {
            _checkoutUiState.value.copy(listCartJajanItem = value)
        }
    }

    fun updateMappedJajan(value: Map<CartJajanItem, Int>) {
        _checkoutUiState.update {
            _checkoutUiState.value.copy(mappedJajan = value)
        }
    }
    fun updateTotalPrice(value: Int) {
        _checkoutUiState.update {
            _checkoutUiState.value.copy(totalPrice = value)
        }
    }

//    fun updateJajanItems(value: Resource<List<JajanItem>>) {
//        _checkoutUiState.update {
//            _checkoutUiState.value.copy(vendorJajanItems = value)
//        }
//    }

    fun updatePageNumber(value: Int) {
        _checkoutUiState.update {
            _checkoutUiState.value.copy(pageNumber = value)
        }
    }


}