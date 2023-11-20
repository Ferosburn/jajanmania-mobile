package com.tokodizital.jajanmania.core.data.vendor.mapper

import com.tokodizital.jajanmania.core.data.vendor.remote.response.JajanItemReponse
import com.tokodizital.jajanmania.core.domain.model.Jajan

fun JajanItemReponse.toDomain() : Jajan {
    return Jajan(
        id = data.id,
        vendorId = data.vendorId,
        name = data.name,
        category = data.categoryId,
        price = data.price.toLong(),
        image = data.imageUrl,
    )
}