package com.tokodizital.jajanmania.core.data.customer.remote.response.vendor

import com.google.gson.annotations.SerializedName

data class NearbyVendorsResponse(
    @SerializedName("data")
    val data: NearbyVendorsData? = null,
    @SerializedName("message")
    val message: String? = null,
)

data class NearbyVendorsData(
    @SerializedName("nearby_vendors")
    val nearbyVendors: List<NearbyVendor>? = null,
)

data class NearbyVendor(
    @SerializedName("vendor")
    val vendor: VendorDetail? = null,
    @SerializedName("distance")
    val distance: Double? = null,
)