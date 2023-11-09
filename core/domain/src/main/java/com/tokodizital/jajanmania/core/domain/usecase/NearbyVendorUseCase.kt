package com.tokodizital.jajanmania.core.domain.usecase

import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.customer.NearbyVendorResult
import kotlinx.coroutines.flow.Flow

interface NearbyVendorUseCase {

    suspend fun getNearbyVendors(
        latitude: Double,
        longitude: Double,
        pageNumber: Int,
        pageSize: Int,
    ): Flow<Resource<List<NearbyVendorResult>>>

}