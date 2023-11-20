package com.tokodizital.jajanmania.vendor.shop.add

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

class FormAddProductViewModel(
    private val vendorUseCase: VendorUseCase,
    private val vendorSessionUseCase: VendorSessionUseCase
) : ViewModel() {

    private val _formAddProduct = MutableStateFlow(FormAddProductUiState())
    val formAddUiState: StateFlow<FormAddProductUiState> get() = _formAddProduct

    val buttonSaveLoading get() = formAddUiState.map {
        it.addJajanResult is Resource.Success ||
        it.postImageResult is Resource.Loading || it.postImageResult is Resource.Success
    }

    val buttonSaveEnabled get() = formAddUiState.map {
        it.productName.isNotEmpty() &&
        it.productCategory.isNotEmpty()
    }

    fun updateName(name: String) {
        _formAddProduct.update {
            _formAddProduct.value.copy(productName = name)
        }
    }

    fun updateToken(token: String) {
        _formAddProduct.update {
            _formAddProduct.value.copy(token = token)
        }
    }

    fun updateId(id: String) {
        _formAddProduct.update {
            _formAddProduct.value.copy(id = id)
        }
    }

    fun updatePrice(price: Int) {
        _formAddProduct.update {
            _formAddProduct.value.copy(productPrice = price)
        }
    }

    fun updateCategory(category: String) {
        _formAddProduct.update {
            _formAddProduct.value.copy(productCategory = category)
        }
    }

    fun updateImageUri(uri: Uri){
        _formAddProduct.update {
            _formAddProduct.value.copy(imageUri = uri)
        }
    }

    fun updateImageUrl(uri: String){
        _formAddProduct.update {
            _formAddProduct.value.copy(productUrl = uri)
        }
    }

    fun updateNameErrorMessage(message: String) {
        _formAddProduct.update {
            _formAddProduct.value.copy(errorMessageName = message)
        }
    }

    fun updateCategoryErrorMessage(message: String) {
        _formAddProduct.update {
            _formAddProduct.value.copy(errorMessageCategory = message)
        }
    }

    fun updatePriceErrorMessage(message: String) {
        _formAddProduct.update {
            _formAddProduct.value.copy(errorMessagePrice = message)
        }
    }


    fun postAddJajan() {
        viewModelScope.launch {
            val token = _formAddProduct.value.token
            val id = _formAddProduct.value.id
            val categoryName = _formAddProduct.value.productCategory
            val name = _formAddProduct.value.productName
            val price = _formAddProduct.value.productPrice
            val url = _formAddProduct.value.productUrl
            val categoryId = _formAddProduct.value.categoriesList.filter {
                it.name == categoryName
            }.first()
            vendorUseCase.postAddJajan(
                token = token,
                id = id,
                category = categoryId.id,
                name = name,
                price = price,
                picture = url,
            ).collect{ result ->
                _formAddProduct.update {
                    it.copy(
                        addJajanResult = result
                    )
                }
            }
        }
    }

    fun postPicture(context: Context) {
        viewModelScope.launch {
            val uri = _formAddProduct.value.imageUri
            val file = uriToFile(uri, context).reduceFileImage()
            val token = _formAddProduct.value.token
            vendorUseCase.postPicture(
                token,
                file,
                ).collect { result ->
                _formAddProduct.update {
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
                    _formAddProduct.update {
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
                _formAddProduct.update {
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
                _formAddProduct.update {
                    it.copy(
                        categoriesResult = result,
                        categoriesList = if (result is Resource.Success) result.data
                        else listOf()
                    )
                }
            }
        }
    }
}