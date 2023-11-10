package com.tokodizital.jajanmania.core.data.customer.remote.service

import com.haroldadmin.cnradapter.NetworkResponse
import com.tokodizital.jajanmania.core.data.customer.remote.response.CommonErrorResponse
import com.tokodizital.jajanmania.core.data.customer.remote.response.VendorsResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface VendorDetailService {

    @GET("api/v1/vendors")
    suspend fun getVendorDetail(
        @Header("Authorization") auth: String,
        @Query("where") where: String,
        @Query("include") include: String,
    ): NetworkResponse<VendorsResponse, CommonErrorResponse>

}