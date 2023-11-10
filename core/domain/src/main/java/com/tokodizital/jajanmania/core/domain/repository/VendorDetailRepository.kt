package com.tokodizital.jajanmania.core.domain.repository

import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.customer.VendorDetail
import kotlinx.coroutines.flow.Flow

interface VendorDetailRepository {

    suspend fun getVendorDetail(
        vendorId: String
    ) : Flow<Resource<VendorDetail>>
}