package com.tokodizital.jajanmania.core.data.vendor.mapper

import com.tokodizital.jajanmania.core.data.vendor.remote.response.AddJajanItemResponse
import com.tokodizital.jajanmania.core.domain.model.vendor.jajan.AddJajanItemResult

fun AddJajanItemResponse.toResult(): AddJajanItemResult {
    return AddJajanItemResult(
        message = message ?: ""
    )
}