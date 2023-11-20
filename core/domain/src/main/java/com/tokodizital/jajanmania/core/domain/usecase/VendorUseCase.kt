package com.tokodizital.jajanmania.core.domain.usecase

import com.tokodizital.jajanmania.core.domain.model.Jajan
import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.UploadPictureResult
import com.tokodizital.jajanmania.core.domain.model.vendor.Category
import com.tokodizital.jajanmania.core.domain.model.vendor.LoginResult
import com.tokodizital.jajanmania.core.domain.model.vendor.LogoutResult
import com.tokodizital.jajanmania.core.domain.model.vendor.RefreshTokenResult
import com.tokodizital.jajanmania.core.domain.model.vendor.RegisterResult
import com.tokodizital.jajanmania.core.domain.model.vendor.Vendor
import com.tokodizital.jajanmania.core.domain.model.vendor.transaction.JajanItem
import com.tokodizital.jajanmania.core.domain.model.vendor.jajan.AddJajanItemResult
import com.tokodizital.jajanmania.core.domain.model.vendor.transaction.TransactionHistoryItem
import kotlinx.coroutines.flow.Flow
import java.io.File

interface VendorUseCase {

    suspend fun login(
        email: String,
        password: String
    ): Flow<Resource<LoginResult>>

    suspend fun register(
        fullName: String,
        username: String,
        email: String,
        gender: String,
        password: String,
    ): Flow<Resource<RegisterResult>>

    suspend fun getTransactionHistory(
        token: String,
        page: Int = 1,
        pageSize: Int = 10,
        vendorId: String
    ): Flow<Resource<List<TransactionHistoryItem>>>

    suspend fun refreshToken(
        accountId: String,
        accountType: String,
        accessToken: String,
        refreshToken: String,
        expiredAt: String
    ): Flow<Resource<RefreshTokenResult>>

    suspend fun getVendor(
        token: String,
        id: String
    ): Flow<Resource<Vendor>>

    suspend fun getShopStatus(
        token: String,
        id: String
    ): Flow<Resource<Boolean>>

    suspend fun updateShopStatus(
        token: String,
        id: String,
        status: Boolean,
        password: String
    ): Flow<Resource<Boolean>>

    suspend fun getDetailTransaction(
        token: String,
        transactionId: String
    ): Flow<Resource<TransactionHistoryItem?>>

    suspend fun postAddJajan(
        token: String,
        id: String,
        category: String,
        name: String,
        price: Int,
        picture: String,
    ): Flow<Resource<AddJajanItemResult>>

    suspend fun postPicture(
        token: String,
        imageFile: File
    ): Flow<Resource<UploadPictureResult>>

    suspend fun getCategories(
        token: String,
        pageNumber: Int = 1,
        pageSize: Int = 100,
    ) : Flow<Resource<List<Category>>>

    suspend fun logout(
        accountId: String,
        accountType: String,
        accessToken: String,
        refreshToken: String,
        expiredAt: String
    ): Flow<Resource<LogoutResult>>

    suspend fun updateJajan(
        token: String,
        id: String,
        category: String,
        name: String,
        price: Int,
        picture: String,
        jajanId: String,
    ): Flow<Resource<AddJajanItemResult>>

    suspend fun getJajanbyId(
        token: String,
        jajanId: String
    ) : Flow<Resource<Jajan>>

    suspend fun deleteJajanbyId(
        token: String,
        jajanId: String
    ) : Flow<Resource<String>>
}