package com.tokodizital.jajanmania.core.data.customer.remote.service

import com.haroldadmin.cnradapter.NetworkResponse
import com.tokodizital.jajanmania.core.data.customer.remote.request.CustomerLoginRequest
import com.tokodizital.jajanmania.core.data.customer.remote.request.CustomerLogoutRequest
import com.tokodizital.jajanmania.core.data.customer.remote.request.CustomerRefreshTokenRequest
import com.tokodizital.jajanmania.core.data.customer.remote.request.CustomerRegisterRequest
import com.tokodizital.jajanmania.core.data.customer.remote.request.CustomerUpdateProfileRequest
import com.tokodizital.jajanmania.core.data.customer.remote.request.SubscriptionRequest
import com.tokodizital.jajanmania.core.data.customer.remote.request.TopUpRequest
import com.tokodizital.jajanmania.core.data.customer.remote.response.CategoriesResponse
import com.tokodizital.jajanmania.core.data.customer.remote.response.CommonErrorResponse
import com.tokodizital.jajanmania.core.data.customer.remote.response.CustomerAccountResponse
import com.tokodizital.jajanmania.core.data.customer.remote.response.CustomerLoginResponse
import com.tokodizital.jajanmania.core.data.customer.remote.response.CustomerLogoutResponse
import com.tokodizital.jajanmania.core.data.customer.remote.response.CustomerRefreshTokenResponse
import com.tokodizital.jajanmania.core.data.customer.remote.response.CustomerRegisterResponse
import com.tokodizital.jajanmania.core.data.customer.remote.response.CustomerResponse
import com.tokodizital.jajanmania.core.data.customer.remote.response.CustomerTransactionHistoryResponse
import com.tokodizital.jajanmania.core.data.customer.remote.response.CustomerUpdateResponse
import com.tokodizital.jajanmania.core.data.customer.remote.response.MySubscriptionResponse
import com.tokodizital.jajanmania.core.data.customer.remote.response.NearbyVendorsResponse
import com.tokodizital.jajanmania.core.data.customer.remote.response.SubscriptionResponse
import com.tokodizital.jajanmania.core.data.customer.remote.response.TopUpResponse
import com.tokodizital.jajanmania.core.data.customer.remote.response.VendorsResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface CustomerJajanManiaService {

    @POST("authentications/users/login?method=email_and_password")
    suspend fun login(
        @Body loginRequest: CustomerLoginRequest
    ): NetworkResponse<CustomerLoginResponse, CommonErrorResponse>

    @POST("authentications/users/logout")
    suspend fun logout(
        @Header("authorization") token: String,
        @Body logoutRequest: CustomerLogoutRequest
    ): NetworkResponse<CustomerLogoutResponse, CommonErrorResponse>

    @POST("authentications/users/register?method=email_and_password")
    suspend fun register(
        @Body registerRequest: CustomerRegisterRequest
    ) : NetworkResponse<CustomerRegisterResponse, CommonErrorResponse>

    @POST("authentications/users/refreshes/access-token")
    suspend fun refreshToken(
        @Body refreshTokenRequest: CustomerRefreshTokenRequest
    ): NetworkResponse<CustomerRefreshTokenResponse, CommonErrorResponse>

    @GET("users/{id}")
    suspend fun getCustomerAccount(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): NetworkResponse<CustomerAccountResponse, CommonErrorResponse>

    @GET("vendors/locations")
    suspend fun getNearbyVendors(
        @Header("Authorization") token: String,
        @Query("distance") distance: Int = 100,
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("page_number") pageNumber: Int = 1,
        @Query("page_size") pageSize: Int = 10,
    ): NetworkResponse<NearbyVendorsResponse, CommonErrorResponse>

    @GET("vendors")
    suspend fun getVendorDetail(
        @Header("Authorization") token: String,
        @Query("where") where: String,
        @Query("include") include: String,
    ): NetworkResponse<VendorsResponse, CommonErrorResponse>

    @GET("users/{userId}")
    suspend fun getCustomer(
        @Header("Authorization") token: String,
        @Path("userId") customerId: String
    ): NetworkResponse<CustomerResponse, CommonErrorResponse>

    @PATCH("users/{userId}")
    suspend fun updateCustomerProfile(
        @Header("Authorization") token: String,
        @Path("userId") userId: String,
        @Body customerUpdateProfileRequest: CustomerUpdateProfileRequest
    ): NetworkResponse<CustomerUpdateResponse, CommonErrorResponse>

    @GET("categories")
    suspend fun getMySubscriptions(
        @Header("Authorization") token: String,
        @Query("page_number") pageNumber: Int = 1,
        @Query("page_size") pageSize: Int = 10,
        @Query("where") where: String,
        @Query("include") include: String? = null,
    ): NetworkResponse<MySubscriptionResponse, CommonErrorResponse>

    @GET("categories")
    suspend fun getCategories(
        @Header("Authorization") token: String,
        @Query("page_number") pageNumber: Int = 1,
        @Query("page_size") pageSize: Int = 10,
        @Query("where") where: String,
        @Query("include") include: String? = null,
    ): NetworkResponse<CategoriesResponse, CommonErrorResponse>

    @POST("user-subscriptions/subscribe")
    suspend fun subscribe(
        @Header("Authorization") token: String,
        @Body subscribeRequest: SubscriptionRequest,
    ): NetworkResponse<SubscriptionResponse, CommonErrorResponse>

    @POST("user-subscriptions/unsubscribe")
    suspend fun unsubscribe(
        @Header("Authorization") token: String,
        @Body unsubscribeRequest: SubscriptionRequest,
    ): NetworkResponse<SubscriptionResponse, CommonErrorResponse>

    @GET("transaction-histories")
    suspend fun getTransactionHistory(
        @Header("Authorization") token: String,
        @Query("page_number") pageNumber: Int = 1,
        @Query("page_size") pageSize: Int = 10,
        @Query("where") where: String,
        @Query("include") include: String,
    ): NetworkResponse<CustomerTransactionHistoryResponse, CommonErrorResponse>

    @POST("top-ups")
    suspend fun topUps(
        @Header("Authorization") token: String,
        @Body topUpRequest: TopUpRequest,
    ): NetworkResponse<TopUpResponse, CommonErrorResponse>
}