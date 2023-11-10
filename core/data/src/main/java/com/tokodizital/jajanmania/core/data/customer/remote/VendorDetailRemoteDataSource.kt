package com.tokodizital.jajanmania.core.data.customer.remote

import com.haroldadmin.cnradapter.NetworkResponse
import com.tokodizital.jajanmania.core.data.customer.remote.response.CommonErrorResponse
import com.tokodizital.jajanmania.core.data.customer.remote.response.VendorsResponse
import com.tokodizital.jajanmania.core.data.customer.remote.service.VendorDetailService

class VendorDetailRemoteDataSource(private val service: VendorDetailService) {

    suspend fun getVendorDetail(
        vendorId: String,
    ) : NetworkResponse<VendorsResponse, CommonErrorResponse> {
        val where = "%7B%22id%22%3A%22$vendorId%22%7D"
        val include = "%7B%22jajanItems%22%3A%7B%22include%22%3A%7B%22category%22%3Atrue%7D%7D%7D"
        val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiI5YjVmYTZmOC1kYjgyLTQ1M2QtYmM1My1lYzU4ZTk4MWJiNGMiLCJhY2NvdW50VHlwZSI6IlVTRVIiLCJpYXQiOjE2OTk1ODU2MjEsImV4cCI6MTY5OTU4NjIyMX0.lg_GTlPdtFdDfhR9cB2ck4ql8lB2bfulugXonqk9INY"
        val auth = "Bearer $token"
        return service.getVendorDetail(auth = auth, where = where, include = include)
    }
}