package com.tokodizital.jajanmania.customer.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.customer.Category
import com.tokodizital.jajanmania.core.domain.model.customer.CustomerRefreshTokenResult
import com.tokodizital.jajanmania.core.domain.usecase.CustomerSessionUseCase
import com.tokodizital.jajanmania.core.domain.usecase.CustomerUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val customerUseCase: CustomerUseCase,
    private val customerSessionUseCase: CustomerSessionUseCase
) : ViewModel() {

    private val _customerHomeUiState = MutableStateFlow(HomeUiState())
    val customerHomeUiState: StateFlow<HomeUiState> get() = _customerHomeUiState

    fun refresh(
        token: String,
        userId: String,
        latitude: Double,
        longitude: Double,
    ) {
        viewModelScope.launch {
            _customerHomeUiState.update {
                _customerHomeUiState.value.copy(isRefresh = true)
            }
            getCustomerAccount(token = token, userId = userId)
            getNearbyVendors(latitude = latitude, longitude = longitude, token = token)
            getMySubscriptions(token = token, userId = userId)
            getCategories(token = token, userId = userId)
            _customerHomeUiState.update {
                _customerHomeUiState.value.copy(isRefresh = false)
            }
        }
    }

    fun getCustomerAccount(
        token: String,
        userId: String
    ) {
        viewModelScope.launch {
            customerUseCase.getCustomerAccount(token, userId).collect { result ->
                _customerHomeUiState.update {
                    it.copy(account = result)
                }
            }
        }
    }

    fun getNearbyVendors(
        latitude: Double,
        longitude: Double,
        token: String,
    ) {
        viewModelScope.launch {
            customerUseCase.getNearbyVendors(
                latitude = latitude,
                longitude = longitude,
                token = token,
                pageSize = 20
            ).collect { result ->
                _customerHomeUiState.update {
                    it.copy(
                        nearbyVendorResult = result,
                    )
                }
            }
        }
    }

    fun getMySubscriptions(
        token: String,
        userId: String,
    ) {
        viewModelScope.launch {
            customerUseCase.getMySubscriptions(
                token = token,
                userId = userId,
            ).collect { result ->
                _customerHomeUiState.update {
                    it.copy(
                        mySubscriptionResult = result,
                        mySubscriptionList = if (result is Resource.Success) result.data else listOf()
                    )
                }
            }
        }
    }

    fun getCategories(
        token: String,
        userId: String,
    ) {
        viewModelScope.launch {
            customerUseCase.getCategories(
                token = token,
                userId = userId,
            ).collect { result ->
                _customerHomeUiState.update {
                    it.copy(
                        categoriesResult = result,
                        categoriesList = if (result is Resource.Success) result.data else listOf()
                    )
                }
            }
        }
    }

    fun subscribe(
        token: String,
        userId: String,
        category: Category
    ) {
        viewModelScope.launch {
            customerUseCase.subscribe(
                token = token,
                userId = userId,
                categoryId = category.id
            ).collect { result ->
                if (result is Resource.Success) {
                    _customerHomeUiState.update {
                        val mySubscriptionItems = it.mySubscriptionList.toMutableList()
                        val mySubscriptionIndex = mySubscriptionItems.indexOf(category)
                        val categoryItems = it.categoriesList.toMutableList()
                        val categoriesIndex = categoryItems.indexOf(category)
                        if (mySubscriptionIndex >= 0) {
                            mySubscriptionItems[mySubscriptionIndex] =
                                mySubscriptionItems[mySubscriptionIndex].copy(isSubscribed = true)
                        }
                        if (categoriesIndex >= 0) {
                            categoryItems[categoriesIndex] =
                                categoryItems[categoriesIndex].copy(isSubscribed = true)
                        }
                        it.copy(
                            mySubscriptionList = mySubscriptionItems,
                            categoriesList = categoryItems
                        )
                    }
                }
            }
        }
    }

    fun unsubscribe(
        token: String,
        userId: String,
        category: Category
    ) {
        viewModelScope.launch {
            customerUseCase.unsubscribe(
                token = token,
                userId = userId,
                categoryId = category.id
            ).collect { result ->
                if (result is Resource.Success) {
                    _customerHomeUiState.update {
                        val mySubscriptionItems = it.mySubscriptionList.toMutableList()
                        val mySubscriptionIndex = mySubscriptionItems.indexOf(category)
                        val categoryItems = it.categoriesList.toMutableList()
                        val categoriesIndex = categoryItems.indexOf(category)
                        if (mySubscriptionIndex >= 0) {
                            mySubscriptionItems[mySubscriptionIndex] =
                                mySubscriptionItems[mySubscriptionIndex].copy(isSubscribed = false)
                        }
                        if (categoriesIndex >= 0) {
                            categoryItems[categoriesIndex] =
                                categoryItems[categoriesIndex].copy(isSubscribed = false)
                        }
                        it.copy(
                            mySubscriptionList = mySubscriptionItems,
                            categoriesList = categoryItems
                        )
                    }
                }
            }
        }
    }

    fun getCustomerSession() {
        viewModelScope.launch {
            customerSessionUseCase.customerSession
                .debounce(1000L)
                .distinctUntilChanged()
                .collectLatest { result ->
                    _customerHomeUiState.update {
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
                _customerHomeUiState.update {
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