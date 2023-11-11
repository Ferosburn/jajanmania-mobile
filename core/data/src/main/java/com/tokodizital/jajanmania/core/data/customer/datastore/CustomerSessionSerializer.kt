package com.tokodizital.jajanmania.core.data.customer.datastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import com.tokodizital.jajanmania.core.data.CustomerSession
import java.io.InputStream
import java.io.OutputStream

object CustomerSessionSerializer: Serializer<CustomerSession> {

    override val defaultValue: CustomerSession
        get() = CustomerSession.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): CustomerSession {
        return try {
            CustomerSession.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: CustomerSession, output: OutputStream) {
        t.writeTo(output)
    }
}