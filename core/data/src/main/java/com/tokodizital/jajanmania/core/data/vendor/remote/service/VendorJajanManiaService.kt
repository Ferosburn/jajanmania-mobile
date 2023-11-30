package com.tokodizital.jajanmania.core.data.vendor.remote.service

import com.haroldadmin.cnradapter.NetworkResponse
import com.tokodizital.jajanmania.core.data.vendor.remote.request.AddJajanRequest
import com.tokodizital.jajanmania.core.data.vendor.remote.request.LoginRequest
import com.tokodizital.jajanmania.core.data.vendor.remote.request.LogoutRequest
import com.tokodizital.jajanmania.core.data.vendor.remote.request.RefreshTokenRequest
import com.tokodizital.jajanmania.core.data.vendor.remote.request.RegisterRequest
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
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface VendorJajanManiaService {

    @POST("authentications/vendors/login?method=email_and_password")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): NetworkResponse<LoginResponse, CommonErrorResponse>

    @POST("authentications/vendors/register?method=email_and_password")
    suspend fun register(
        @Body registerRequest: RegisterRequest
    ): NetworkResponse<RegisterResponse, CommonErrorResponse>

    @GET("transaction-histories")
    suspend fun getTransactionHistory(
        @Header("authorization") token: String,
        @Query("page_number") page: Int = 1,
        @Query("page_size") pageSize: Int = 10,
        @Query("where") where: String,
        @Query("include") include: String
    ): NetworkResponse<TransactionHistoryResponse, CommonErrorResponse>

    @POST("authentications/vendors/refreshes/access-token")
    suspend fun refreshToken(
        @Body refreshTokenRequest: RefreshTokenRequest
    ): NetworkResponse<RefreshTokenResponse, CommonErrorResponse>

    @GET("vendors/{id}")
    suspend fun getVendor(
        @Header("authorization") token: String,
        @Path("id") id: String
    ): NetworkResponse<VendorResponse, CommonErrorResponse>

    @PATCH("vendors/{id}")
    suspend fun updateShopStatus(
        @Header("authorization") token: String,
        @Path("id") id: String,
        @Body updateShopStatusRequest: UpdateShopStatusRequest
    ): NetworkResponse<VendorResponse, CommonErrorResponse>

    @GET("transaction-histories")
    suspend fun getDetailTransactionHistory(
        @Header("authorization") token: String,
        @Query("where") where: String,
        @Query("include") include: String
    ): NetworkResponse<TransactionHistoryResponse, CommonErrorResponse>

    @POST("files")
    @Multipart
    suspend fun postImage(
        @Header("authorization") token: String,
        @Part picture: MultipartBody.Part,
    ): NetworkResponse<UploadPictureResponse, CommonErrorResponse>

    @POST("jajan-items")
    suspend fun postAddJajan(
        @Header("authorization") token: String,
        @Body addJajanRequest: AddJajanRequest
    ): NetworkResponse<AddJajanItemResponse, CommonErrorResponse>


    @GET("categories")
    suspend fun getCategories(
        @Header("Authorization") token: String,
        @Query("page_number") pageNumber: Int = 1,
        @Query("page_size") pageSize: Int = 100,
        @Query("where") where: String? = null,
        @Query("include") include: String? = null,
    ): NetworkResponse<CategoriesResponse, CommonErrorResponse>

    @POST("authentications/vendors/logout")
    suspend fun logout(
        @Header("authorization") token: String,
        @Body logoutRequest: LogoutRequest
    ): NetworkResponse<LogoutResponse, CommonErrorResponse>

    @GET("jajan-items")
    suspend fun getJajanItems(
        @Header("authorization") token: String,
        @Query("page_number") page: Int = 1,
        @Query("page_size") pageSize: Int = 10,
        @Query("where") where: String,
        @Query("include") include: String
    ): NetworkResponse<ListJajanResponse, CommonErrorResponse>
  
    @PATCH("jajan-items/{id}")
      suspend fun updateJajan(
          @Header("authorization") token: String,
          @Body addJajanRequest: AddJajanRequest,
          @Path("id") id: String,
      ): NetworkResponse<AddJajanItemResponse, CommonErrorResponse>

    @GET("jajan-items/{id}")
    suspend fun getJajanById(
        @Header("Authorization") token: String,
        @Path("id") id: String,
    ): NetworkResponse<JajanItemReponse, CommonErrorResponse>

    @DELETE("jajan-items/{id}")
    suspend fun deleteJajanById(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Query("method") method: String
    ): NetworkResponse<DeleteJajanReponse, CommonErrorResponse>
}