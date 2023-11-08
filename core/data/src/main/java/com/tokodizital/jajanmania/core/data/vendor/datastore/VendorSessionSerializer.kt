package com.tokodizital.jajanmania.core.data.vendor.datastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import com.tokodizital.jajanmania.core.data.VendorSession
import java.io.InputStream
import java.io.OutputStream

object VendorSessionSerializer : Serializer<VendorSession> {

    override val defaultValue: VendorSession
        get() = VendorSession.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): VendorSession {
        return try {
            VendorSession.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: VendorSession, output: OutputStream) {
        t.writeTo(output)
    }

}