package com.tokodizital.jajanmania.core.domain.repository

import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.customer.NearbyVendorResult
import kotlinx.coroutines.flow.Flow

interface NearbyVendorRepository {

    suspend fun getNearbyVendors(
        latitude: Double,
        longitude: Double,
        pageNumber: Int,
        pageSize: Int,
    ): Flow<Resource<List<NearbyVendorResult>>>
}