package com.tokodizital.jajanmania.vendor.shop.add

import android.net.Uri
import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.UploadPictureResult
import com.tokodizital.jajanmania.core.domain.model.vendor.Category
import com.tokodizital.jajanmania.core.domain.model.vendor.RefreshTokenResult
import com.tokodizital.jajanmania.core.domain.model.vendor.VendorSession
import com.tokodizital.jajanmania.core.domain.model.vendor.jajan.AddJajanItemResult

data class FormAddProductUiState(
    val productName: String = "",
    val productPrice: Int = 0,
    val productCategory: String = "",
    val productUrl: String = "",
    val imageUri: Uri = Uri.EMPTY,
    val errorMessagePrice: String = "",
    val errorMessageName: String = "",
    val errorMessageCategory: String = "",
    val vendorSession: Resource<VendorSession> = Resource.Loading,
    val refreshToken: Resource<RefreshTokenResult> = Resource.Loading,
    val addJajanResult: Resource<AddJajanItemResult> = Resource.Empty,
    val postImageResult: Resource<UploadPictureResult> = Resource.Empty,
    val categoriesResult: Resource<List<Category>> = Resource.Empty,
    val categoriesList: List<Category> = listOf(),
    val token: String = "",
    val id: String = "",
)