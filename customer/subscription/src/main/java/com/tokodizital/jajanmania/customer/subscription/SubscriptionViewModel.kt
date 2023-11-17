package com.tokodizital.jajanmania.customer.subscription

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
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SubscriptionViewModel(
    private val customerUseCase: CustomerUseCase,
    private val customerSessionUseCase: CustomerSessionUseCase
) : ViewModel() {

    private val _subscriptionUiState = MutableStateFlow(SubscriptionUiState())
    val subscriptionUiState: StateFlow<SubscriptionUiState> get() = _subscriptionUiState
    val loadMoreButtonIsLoading get() = subscriptionUiState.map { it.subscription is Resource.Loading }

    fun getMySubscriptions(
        token: String,
        userId: String,
        pageNumber: Int = 1,
    ) {
        viewModelScope.launch {
            customerUseCase.getMySubscriptions(
                token = token,
                userId = userId,
                pageNumber = pageNumber,
            ).collect { result ->
                _subscriptionUiState.update {
                    val items = it.categories + if (result is Resource.Success) result.data else listOf()
                    it.copy(
                        subscription = result,
                        categories = items
                    )
                }
            }
        }
    }

    fun getCategories(
        token: String,
        userId: String,
        pageNumber: Int = 1,
    ) {
        viewModelScope.launch {
            customerUseCase.getCategories(
                token = token,
                userId = userId,
                pageNumber = pageNumber,
            ).collect { result ->
                _subscriptionUiState.update {
                    val items = it.categories + if (result is Resource.Success) result.data else listOf()
                    it.copy(
                        subscription = result,
                        categories = items
                    )
                }
            }
        }
    }

    fun addPageCount() {
        _subscriptionUiState.update {
            it.copy(pageCount = it.pageCount + 1)
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
                    _subscriptionUiState.update {
                        val items = it.categories.toMutableList()
                        val index = items.indexOf(category)
                        items[index] = items[index].copy(isSubscribed = true)
                        it.copy(categories = items)
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
                    _subscriptionUiState.update {
                        val items = it.categories.toMutableList()
                        val index = items.indexOf(category)
                        items[index] = items[index].copy(isSubscribed = false)
                        it.copy(categories = items)
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
                    _subscriptionUiState.update {
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
                _subscriptionUiState.update {
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