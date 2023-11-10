package com.tokodizital.jajanmania.core.domain.usecase

import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.customer.VendorDetail
import kotlinx.coroutines.flow.Flow

interface VendorDetailUseCase {

    suspend fun getVendorDetail(
        vendorId: String
    ) : Flow<Resource<VendorDetail>>
}