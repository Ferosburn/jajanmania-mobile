package com.tokodizital.jajanmania.core.domain.interactor

import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.customer.NearbyVendorResult
import com.tokodizital.jajanmania.core.domain.repository.NearbyVendorRepository
import com.tokodizital.jajanmania.core.domain.usecase.NearbyVendorUseCase
import kotlinx.coroutines.flow.Flow

class NearbyVendorInteractor(
    private val nearbyVendorRepository: NearbyVendorRepository
) : NearbyVendorUseCase {

    override suspend fun getNearbyVendors(
        latitude: Double,
        longitude: Double,
        pageNumber: Int,
        pageSize: Int
    ): Flow<Resource<List<NearbyVendorResult>>> {
        return nearbyVendorRepository.getNearbyVendors(latitude, longitude, pageNumber, pageSize)
    }
}