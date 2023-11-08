package com.tokodizital.jajanmania.core.data.vendor.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.dataStore
import com.tokodizital.jajanmania.core.data.VendorSession
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

private val Context.vendorSessionDataStore: DataStore<VendorSession> by dataStore(
    fileName = VendorSessionDataSource.DATA_STORE_FILE_NAME,
    serializer = VendorSessionSerializer
)

class VendorSessionDataSource(
    private val context: Context
) {

    companion object {
        const val DATA_STORE_FILE_NAME = "vendor_session.pb"
    }

    private val vendorSessionDataStore get() = context.vendorSessionDataStore

    val vendorSession: Flow<VendorSession> get() = vendorSessionDataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(VendorSession.getDefaultInstance())
            } else {
                throw exception
            }
        }

    suspend fun updateVendorSession(
        accountId: String,
        accountType: String,
        accessToken: String,
        refreshToken: String,
        expiredAt: String
    ) {
        vendorSessionDataStore.updateData { vendorSession ->
            vendorSession.toBuilder()
                .setAccountType(accountType)
                .setAccountId(accountId)
                .setAccessToken(accessToken)
                .setRefreshToken(refreshToken)
                .setExpiredAt(expiredAt)
                .build()
        }
    }

    suspend fun deleteVendorSession() {
        vendorSessionDataStore.updateData { it.toBuilder().clear().build() }
    }

}