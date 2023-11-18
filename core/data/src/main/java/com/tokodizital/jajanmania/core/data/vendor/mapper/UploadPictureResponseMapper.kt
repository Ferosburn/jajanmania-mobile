package com.tokodizital.jajanmania.core.data.vendor.mapper

import com.tokodizital.jajanmania.core.data.vendor.remote.response.UploadPictureResponse
import com.tokodizital.jajanmania.core.domain.model.UploadPictureResult

fun UploadPictureResponse.toResult(): UploadPictureResult {
    return UploadPictureResult(
        message = message,
        url = data.url,
    )
}