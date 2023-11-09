package com.tokodizital.jajanmania.core.data.customer.remote

import com.haroldadmin.cnradapter.NetworkResponse
import com.tokodizital.jajanmania.core.data.customer.remote.response.CommonErrorResponse
import com.tokodizital.jajanmania.core.data.customer.remote.response.NearbyVendorsResponse
import com.tokodizital.jajanmania.core.data.customer.remote.service.NearbyVendorsService

class NearbyVendorRemoteDataSource(private val service: NearbyVendorsService) {

    suspend fun getNearbyVendors(
        latitude: Double,
        longitude: Double,
        pageNumber: Int,
        pageSize: Int
    ): NetworkResponse<NearbyVendorsResponse, CommonErrorResponse> {
        val token =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiI5YjVmYTZmOC1kYjgyLTQ1M2QtYmM1My1lYzU4ZTk4MWJiNGMiLCJhY2NvdW50VHlwZSI6IlVTRVIiLCJpYXQiOjE2OTk1NzE0NzIsImV4cCI6MTY5OTU3MjA3Mn0.1iuDMzAvJWz4FLNWX7uOdJOPfRUjj21guo9h4wketWU"
        val auth = "Bearer $token"
        return service.getNearbyVendors(
            auth = auth,
            distance = 100,
            latitude = latitude,
            longitude = longitude,
            pageNumber = pageNumber,
            pageSize = pageSize
        )
    }

}