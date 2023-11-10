package com.tokodizital.jajanmania.customer.vendor

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.usecase.VendorDetailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CustomerVendorDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val vendorDetailUseCase: VendorDetailUseCase,
) : ViewModel() {

    private val _customerVendorDetailUiState = MutableStateFlow(CustomerVendorDetailUiState())
    val customerVendorDetailUiState: StateFlow<CustomerVendorDetailUiState> get() = _customerVendorDetailUiState

    private val vendorId: String = checkNotNull(savedStateHandle["vendorId"])

    init {
        getVendorDetail(vendorId)
    }

    private fun getVendorDetail(vendorId: String) {
        viewModelScope.launch {
            vendorDetailUseCase.getVendorDetail(vendorId).collect { result ->
                if (result is Resource.Success) {
                    _customerVendorDetailUiState.update {
                        it.copy(
                            name = result.data.name,
                            description = result.data.description,
                            image = result.data.image,
                            jajanItems = result.data.jajanItems,
                        )
                    }
                }
            }
        }
    }
}