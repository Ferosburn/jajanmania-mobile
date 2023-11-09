package com.tokodizital.jajanmania.core.data.customer.remote.service

import com.haroldadmin.cnradapter.NetworkResponse
import com.tokodizital.jajanmania.core.data.customer.remote.response.CommonErrorResponse
import com.tokodizital.jajanmania.core.data.customer.remote.response.NearbyVendorsResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NearbyVendorsService {

    @GET("api/v1/vendors/locations")
    suspend fun getNearbyVendors(
        @Header("Authorization") auth: String,
        @Query("distance") distance: Int,
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("page_number") pageNumber: Int,
        @Query("page_size") pageSize: Int,
    ): NetworkResponse<NearbyVendorsResponse, CommonErrorResponse>

}