package com.tokodizital.jajanmania.core.data.customer.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.dataStore
import com.tokodizital.jajanmania.core.data.CustomerSession
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

private val Context.customerSessionDataStore: DataStore<CustomerSession> by dataStore(
    fileName = CustomerSessionDataSource.DATA_STORE_FILE_NAME,
    serializer = CustomerSessionSerializer
)

class CustomerSessionDataSource(
    private val context: Context
) {
    companion object {
        const val DATA_STORE_FILE_NAME = "customer_session.pb"
    }

    private val customerSessionDataStore get() = context.customerSessionDataStore

    val customerSession: Flow<CustomerSession>
        get() = customerSessionDataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(CustomerSession.getDefaultInstance())
            } else {
                throw exception
            }
        }

    suspend fun updateCustomerSession(
        accountId: String,
        accountType: String,
        accessToken: String,
        refreshToken: String,
        expiredAt: String,
        firebaseToken: String
    ) {
        customerSessionDataStore.updateData { customerSession ->
            customerSession.toBuilder()
                .setAccountId(accountId)
                .setAccountType(accountType)
                .setAccessToken(accessToken)
                .setRefreshToken(refreshToken)
                .setExpiredAt(expiredAt)
                .setFirebaseToken(firebaseToken)
                .build()
        }
    }

    suspend fun deleteCustomerSession() {
        customerSessionDataStore.updateData { it.toBuilder().clear().build() }
    }
}