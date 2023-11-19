package com.tokodizital.jajanmania.core.data.vendor.remote

import com.haroldadmin.cnradapter.NetworkResponse
import com.tokodizital.jajanmania.core.data.vendor.remote.request.AddJajanRequest
import com.tokodizital.jajanmania.core.data.vendor.remote.request.LoginRequest
import com.tokodizital.jajanmania.core.data.vendor.remote.request.LogoutDataSessionRequest
import com.tokodizital.jajanmania.core.data.vendor.remote.request.LogoutRequest
import com.tokodizital.jajanmania.core.data.vendor.remote.request.RefreshTokenRequest
import com.tokodizital.jajanmania.core.data.vendor.remote.request.RefreshTokenSessionRequest
import com.tokodizital.jajanmania.core.data.vendor.remote.request.UpdateShopStatusRequest
import com.tokodizital.jajanmania.core.data.vendor.remote.response.AddJajanItemResponse
import com.tokodizital.jajanmania.core.data.vendor.remote.response.CategoriesResponse
import com.tokodizital.jajanmania.core.data.vendor.remote.response.CommonErrorResponse
import com.tokodizital.jajanmania.core.data.vendor.remote.response.DeleteJajanReponse
import com.tokodizital.jajanmania.core.data.vendor.remote.response.JajanItemReponse
import com.tokodizital.jajanmania.core.data.vendor.remote.response.ListJajanResponse
import com.tokodizital.jajanmania.core.data.vendor.remote.response.LoginResponse
import com.tokodizital.jajanmania.core.data.vendor.remote.response.LogoutResponse
import com.tokodizital.jajanmania.core.data.vendor.remote.response.RefreshTokenResponse
import com.tokodizital.jajanmania.core.data.vendor.remote.response.RegisterResponse
import com.tokodizital.jajanmania.core.data.vendor.remote.response.UploadPictureResponse
import com.tokodizital.jajanmania.core.data.vendor.remote.response.VendorResponse
import com.tokodizital.jajanmania.core.data.vendor.remote.response.transaction.TransactionHistoryResponse
import com.tokodizital.jajanmania.core.data.vendor.remote.service.VendorJajanManiaService
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class VendorJajanManiaRemoteDataSource(private val service: VendorJajanManiaService) {

    suspend fun login(
        email: String,
        password: String
    ): NetworkResponse<LoginResponse, CommonErrorResponse> {
        val loginRequest = LoginRequest(email, password)
        return service.login(loginRequest)
    }

    suspend fun register(
        fullName: String,
        username: String,
        email: String,
        gender: String,
        password: String
    ): NetworkResponse<RegisterResponse, CommonErrorResponse> {
        return service.register(fullName, username, email, gender, password)
    }

    suspend fun getTransactionHistory(
        token: String,
        page: Int = 1,
        pageSize: Int = 10,
        vendorId: String,
    ): NetworkResponse<TransactionHistoryResponse, CommonErrorResponse> {
        val authorization = "Bearer $token"
        val whereParams = "%7B%22transactionItems%22%3A%20%7B%22some%22%3A%20%7B%22jajanItem%22%3A%20%7B%22is%22%3A%20%7B%22vendor%22%3A%20%7B%22is%22%3A%20%7B%22id%22%3A%20%22$vendorId%22%7D%7D%7D%7D%7D%7D%7D"
        val includeParams = "%7B%22transactionItems%22%3A%20%7B%22include%22%20%3A%20%7B%22jajanItem%22%3A%20true%7D%7D%7D"
        return service.getTransactionHistory(
            authorization,
            page,
            pageSize,
            whereParams,
            includeParams
        )
    }

    suspend fun refreshToken(
        accountId: String,
        accountType: String,
        accessToken: String,
        refreshToken: String,
        expiredAt: String
    ): NetworkResponse<RefreshTokenResponse, CommonErrorResponse> {
        val refreshTokenSessionRequest = RefreshTokenSessionRequest(
            accessToken, refreshToken, accountType, accountId, expiredAt
        )
        val refreshTokenRequest = RefreshTokenRequest(
            session = refreshTokenSessionRequest
        )
        return service.refreshToken(
            refreshTokenRequest
        )
    }

    suspend fun getVendor(
        token: String,
        id: String
    ): NetworkResponse<VendorResponse, CommonErrorResponse> {
        val authorization = "Bearer $token"
        return service.getVendor(authorization, id)
    }

    suspend fun updateShopStatus(
        token: String,
        id: String,
        status: Boolean,
        password: String
    ): NetworkResponse<VendorResponse, CommonErrorResponse> {
        val authorization = "Bearer $token"
        val shopStatus = if (status) "ON" else "OFF"
        val updateShopStatusRequest = UpdateShopStatusRequest(
            password, password, shopStatus
        )
        return service.updateShopStatus(authorization, id, updateShopStatusRequest)
    }

    suspend fun getDetailTransactionHistory(
        token: String,
        transactionId: String,
    ): NetworkResponse<TransactionHistoryResponse, CommonErrorResponse> {
        val authorization = "Bearer $token"
        val whereParams = "%7B%22id%22%3A%22$transactionId%22%7D"
        val includeParams = "%7B%22transactionItems%22%3A%7B%22include%22%3A%7B%22jajanItem%22%3Atrue%7D%7D%7D"
        return service.getDetailTransactionHistory(
            authorization,
            whereParams,
            includeParams
        )
    }

    suspend fun postAddJajan(
        token: String,
        vendorId: String,
        categoryId: String,
        name: String,
        price: Int,
        imageUrl: String,
    ): NetworkResponse<AddJajanItemResponse, CommonErrorResponse> {
        val authorization = "Bearer $token"
        val jajanRequest = AddJajanRequest(
            categoryId = categoryId,
            vendorId = vendorId,
            name = name,
            price = price,
            imageUrl = imageUrl,
        )
        return service.postAddJajan(authorization,jajanRequest)
    }

    suspend fun postPicture(
        token: String,
        imageFile: File
    ): NetworkResponse<UploadPictureResponse, CommonErrorResponse> {
        val authorization = "Bearer $token"
        val requestImageFile = imageFile.asRequestBody("image/jpeg".toMediaType())
        val imagePart = MultipartBody.Part.createFormData("picture", imageFile.name, requestImageFile)
        return service.postImage(
            authorization,
            imagePart
        )
    }

    suspend fun getCategories(
        token: String,
        pageNumber: Int,
        pageSize: Int,
    ) : NetworkResponse<CategoriesResponse, CommonErrorResponse> {
        val bearerToken = "Bearer $token"
        return service.getCategories(
            token = bearerToken,
            pageNumber = pageNumber,
            pageSize = pageSize,
        )
    }

    suspend fun logout(
        accountId: String,
        accountType: String,
        accessToken: String,
        refreshToken: String,
        expiredAt: String
    ): NetworkResponse<LogoutResponse, CommonErrorResponse> {
        val authorization = "Bearer $accessToken"
        val refreshTokenSessionRequest = LogoutDataSessionRequest(
            accessToken, refreshToken, accountType, accountId, expiredAt
        )
        val refreshTokenRequest = LogoutRequest(
            session = refreshTokenSessionRequest
        )
        return service.logout(
            authorization,
            refreshTokenRequest
        )
    }

    suspend fun getListJajan(
        token: String,
        vendorId: String,
        page: Int = 1,
        pageSize: Int = 10,
    ): NetworkResponse<ListJajanResponse, CommonErrorResponse> {
        val authorization = "Bearer $token"
        val whereParams = "%7B%22AND%22%3A%5B%7B%22vendorId%22%3A%22$vendorId%22%7D%2C%7B%22deletedAt%22%3Anull%7D%5D%7D"
        val includeParams = "%7B%22category%22%3Atrue%7D"
        return service.getJajanItems(
            authorization,
            page,
            pageSize,
            whereParams,
            includeParams
        )
    }

    suspend fun updateJajan(
        token: String,
        vendorId: String,
        categoryId: String,
        name: String,
        price: Int,
        imageUrl: String,
        jajanId: String,
    ): NetworkResponse<AddJajanItemResponse, CommonErrorResponse> {
        val authorization = "Bearer $token"
        val jajanRequest = AddJajanRequest(
            categoryId = categoryId,
            vendorId = vendorId,
            name = name,
            price = price,
            imageUrl = imageUrl,
        )
        return service.updateJajan(authorization,jajanRequest,jajanId)
    }

    suspend fun getJajanById(
        token: String,
        jajanId: String,
    ) : NetworkResponse<JajanItemReponse, CommonErrorResponse> {
        val bearerToken = "Bearer $token"
        return service.getJajanById(
            token = bearerToken,
            id = jajanId,
        )
    }

    suspend fun deleteJajanById(
        token: String,
        jajanId: String,
        method: String = "soft"
    ) : NetworkResponse<DeleteJajanReponse, CommonErrorResponse> {
        val bearerToken = "Bearer $token"
        return service.deleteJajanById(
            token = bearerToken,
            id = jajanId,
            method = method
        )
    }
}