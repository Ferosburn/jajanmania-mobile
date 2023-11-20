package com.tokodizital.jajanmania.vendor.shop.edit

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tokodizital.jajanmania.common.utils.reduceFileImage
import com.tokodizital.jajanmania.common.utils.uriToFile
import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.vendor.LoginResult
import com.tokodizital.jajanmania.core.domain.usecase.VendorSessionUseCase
import com.tokodizital.jajanmania.core.domain.usecase.VendorUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FormUpdateProductViewModel(
    private val vendorUseCase: VendorUseCase,
    private val vendorSessionUseCase: VendorSessionUseCase
) : ViewModel() {

    private val _formUpdateUiState = MutableStateFlow(FormUpdateProductUiState())
    val fromUpdateUiState: StateFlow<FormUpdateProductUiState> get() = _formUpdateUiState

    val buttonSaveLoading get() = fromUpdateUiState.map {
        it.addJajanResult is Resource.Success ||
        it.postImageResult is Resource.Loading || it.postImageResult is Resource.Success
    }

    val buttonSaveEnabled get() = fromUpdateUiState.map {
        it.productName.isNotEmpty() &&
        it.productCategory.isNotEmpty()
    }

    fun updateName(name: String) {
        _formUpdateUiState.update {
            _formUpdateUiState.value.copy(productName = name)
        }
    }

    fun updateToken(token: String) {
        _formUpdateUiState.update {
            _formUpdateUiState.value.copy(token = token)
        }
    }

    fun updateId(id: String) {
        _formUpdateUiState.update {
            _formUpdateUiState.value.copy(id = id)
        }
    }

    fun updatePrice(price: Int) {
        _formUpdateUiState.update {
            _formUpdateUiState.value.copy(productPrice = price)
        }
    }

    fun updateCategory(category: String) {
        _formUpdateUiState.update {
            _formUpdateUiState.value.copy(productCategory = category)
        }
    }

    fun updateImageUri(uri: Uri){
        _formUpdateUiState.update {
            _formUpdateUiState.value.copy(imageUri = uri)
        }
    }

    fun updateImageUrl(uri: String){
        _formUpdateUiState.update {
            _formUpdateUiState.value.copy(productUrl = uri)
        }
    }

    fun updateNameErrorMessage(message: String) {
        _formUpdateUiState.update {
            _formUpdateUiState.value.copy(errorMessageName = message)
        }
    }

    fun updateCategoryErrorMessage(message: String) {
        _formUpdateUiState.update {
            _formUpdateUiState.value.copy(errorMessageCategory = message)
        }
    }

    fun updatePriceErrorMessage(message: String) {
        _formUpdateUiState.update {
            _formUpdateUiState.value.copy(errorMessagePrice = message)
        }
    }


    fun updateJajan(
        jajanId: String
    ) {
        viewModelScope.launch {
            val token = _formUpdateUiState.value.token
            val id = _formUpdateUiState.value.id
            val categoryName = _formUpdateUiState.value.productCategory
            val name = _formUpdateUiState.value.productName
            val price = _formUpdateUiState.value.productPrice
            val url = _formUpdateUiState.value.productUrl
            val categoryId = _formUpdateUiState.value.categoriesList.filter {
                it.name == categoryName
            }.first()
            vendorUseCase.updateJajan(
                token = token,
                id = id,
                category = categoryId.id,
                name = name,
                price = price,
                picture = url,
                jajanId = jajanId,
            ).collect{ result ->
                _formUpdateUiState.update {
                    it.copy(
                        addJajanResult = result
                    )
                }
            }
        }
    }

    fun postPicture(context: Context) {
        viewModelScope.launch {
            val uri = _formUpdateUiState.value.imageUri
            val file = uriToFile(uri, context).reduceFileImage()
            val token = _formUpdateUiState.value.token
            vendorUseCase.postPicture(
                token,
                file,
                ).collect { result ->
                _formUpdateUiState.update {
                    it.copy(
                        postImageResult = result
                    )
                }
            }
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
            vendorSessionUseCase.vendorSession
                .debounce(1000L)
                .distinctUntilChanged()
                .collectLatest { result ->
                    _formUpdateUiState.update {
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
                _formUpdateUiState.update {
                    it.copy(
                        refreshToken = result
                    )
                }
            }
        }
    }

    fun getCategories(
        token: String,
    ) {
        viewModelScope.launch {
            vendorUseCase.getCategories(
                token = token,
            ).collect { result ->
                _formUpdateUiState.update {
                    it.copy(
                        categoriesResult = result,
                        categoriesList = if (result is Resource.Success) result.data
                        else listOf()
                    )
                }
            }
        }
    }

    fun getJajanById(
        token: String,
        jajanId: String,
    ) {
        viewModelScope.launch {
            vendorUseCase.getJajanbyId(
                token = token,
                jajanId,
            ).collect { result ->

                _formUpdateUiState.update {
                    it.copy(
                        getJajanResult = result,
                        productName = if (result is Resource.Success) result.data.name else "",
                        productPrice = if (result is Resource.Success) result.data.price.toInt() else 0,
                        productCategory = if (result is Resource.Success) {
                            _formUpdateUiState.value.categoriesList.filter {
                                it.id == result.data.category
                            }.map {
                                it.name
                            }.first()
                        } else "",
                        productUrl = if (result is Resource.Success) result.data.image else "",
                    )
                }
            }
        }
    }

    fun deleteJajanById(
        jajanId: String,
    ) {
        viewModelScope.launch {
            val token = _formUpdateUiState.value.token
            vendorUseCase.deleteJajanbyId(
                token = token,
                jajanId = jajanId
            ).collect { result ->
                _formUpdateUiState.update {
                    it.copy(
                        deleteJajanResult = result,
                    )
                }
            }
        }
    }
}