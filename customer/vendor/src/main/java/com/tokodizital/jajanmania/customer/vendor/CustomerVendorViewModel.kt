package com.tokodizital.jajanmania.customer.vendor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.usecase.NearbyVendorUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CustomerVendorViewModel(
    private val nearbyVendorUseCase: NearbyVendorUseCase
) : ViewModel() {

    private val _customerVendorUiState = MutableStateFlow(CustomerVendorUiState())
    val customerVendorUiState: StateFlow<CustomerVendorUiState> get() = _customerVendorUiState

    init {
        getNearbyVendors()
    }

    private fun getNearbyVendors() {
        viewModelScope.launch {
            val latitude = _customerVendorUiState.value.latitude
            val longitude = _customerVendorUiState.value.longitude
            val pageNumber = _customerVendorUiState.value.pageNumber
            nearbyVendorUseCase.getNearbyVendors(latitude, longitude, pageNumber, pageSize = 10)
                .collect { result ->
                    if (result is Resource.Success) {
                        _customerVendorUiState.update {
                            it.copy(
                                nearbyVendorsResult = result,
                                nearbyVendors = result.data
                            )
                        }
                    }
                }
        }
    }
}