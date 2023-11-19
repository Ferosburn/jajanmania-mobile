package com.tokodizital.jajanmania.core.data.vendor.mapper

import com.tokodizital.jajanmania.core.data.vendor.remote.response.JajanItemsItem
import com.tokodizital.jajanmania.core.domain.model.Jajan

fun JajanItemsItem.toDomain(): Jajan {
    return Jajan(
        category = category.name,
        price = price.toLong(),
        image = imageUrl,
        vendorId = vendorId,
        name = name,
        id = id
    )
}
