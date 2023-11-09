package com.tokodizital.jajanmania.core.data.customer.remote.response

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
    val vendor: Vendor? = null,
    @SerializedName("distance")
    val distance: Double? = null,
)

data class Vendor(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("jajan_name")
    val name: String? = null,
    @SerializedName("jajan_description")
    val description: String? = null,
    @SerializedName("jajan_image_url")
    val image: String? = null,
)