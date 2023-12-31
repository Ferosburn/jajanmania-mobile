package com.tokodizital.jajanmania.core.domain.interactor

import com.tokodizital.jajanmania.core.domain.model.Jajan
import com.tokodizital.jajanmania.core.domain.model.Resource
import com.tokodizital.jajanmania.core.domain.model.UploadPictureResult
import com.tokodizital.jajanmania.core.domain.model.vendor.Category
import com.tokodizital.jajanmania.core.domain.model.vendor.LoginResult
import com.tokodizital.jajanmania.core.domain.model.vendor.LogoutResult
import com.tokodizital.jajanmania.core.domain.model.vendor.RefreshTokenResult
import com.tokodizital.jajanmania.core.domain.model.vendor.RegisterResult
import com.tokodizital.jajanmania.core.domain.model.vendor.Vendor
import com.tokodizital.jajanmania.core.domain.model.vendor.jajan.AddJajanItemResult
import com.tokodizital.jajanmania.core.domain.model.vendor.transaction.TransactionHistoryItem
import com.tokodizital.jajanmania.core.domain.repository.VendorRepository
import com.tokodizital.jajanmania.core.domain.usecase.VendorUseCase
import kotlinx.coroutines.flow.Flow
import java.io.File

class VendorInteractor(
    private val vendorRepository: VendorRepository
) : VendorUseCase {

    override suspend fun login(email: String, password: String): Flow<Resource<LoginResult>> {
        return vendorRepository.login(email, password)
    }

    override suspend fun register(
        fullName: String,
        username: String,
        email: String,
        gender: String,
        password: String
    ): Flow<Resource<RegisterResult>> {
        return vendorRepository.register(fullName, username, email, gender, password)
    }

    override suspend fun getTransactionHistory(
        token: String,
        page: Int,
        pageSize: Int,
        vendorId: String
    ): Flow<Resource<List<TransactionHistoryItem>>> {
        return vendorRepository.getTransactionHistory(token, page, pageSize, vendorId)
    }

    override suspend fun refreshToken(
        accountId: String,
        accountType: String,
        accessToken: String,
        refreshToken: String,
        expiredAt: String
    ): Flow<Resource<RefreshTokenResult>> {
        return vendorRepository.refreshToken(
            accountId,
            accountType,
            accessToken,
            refreshToken,
            expiredAt
        )
    }

    override suspend fun getVendor(token: String, id: String): Flow<Resource<Vendor>> {
        return vendorRepository.getVendor(token, id)
    }

    override suspend fun getShopStatus(token: String, id: String): Flow<Resource<Boolean>> {
        return vendorRepository.getShopStatus(token, id)
    }

    override suspend fun updateShopStatus(
        token: String,
        id: String,
        status: Boolean,
        password: String
    ): Flow<Resource<Boolean>> {
        return vendorRepository.updateShopStatus(token, id, status, password)
    }

    override suspend fun getDetailTransaction(
        token: String,
        transactionId: String
    ): Flow<Resource<TransactionHistoryItem?>> {
        return vendorRepository.getDetailTransaction(token, transactionId)
    }

    override suspend fun postAddJajan(
        token: String,
        id: String,
        category: String,
        name: String,
        price: Int,
        picture: String
    ): Flow<Resource<AddJajanItemResult>> {
        return vendorRepository.postAddJajan(
            token,
            id,
            category,
            name,
            price,
            picture
        )
    }

    override suspend fun postPicture(
        token: String,
        imageFile: File
    ): Flow<Resource<UploadPictureResult>> {
        return vendorRepository.postPicture(token, imageFile)
    }

    override suspend fun getCategories(
        token: String,
        pageNumber: Int,
        pageSize: Int,
    ): Flow<Resource<List<Category>>> {
        return vendorRepository.getCategories(
            token, pageNumber, pageSize
        )
    }

    override suspend fun logout(
        accountId: String,
        accountType: String,
        accessToken: String,
        refreshToken: String,
        expiredAt: String
    ): Flow<Resource<LogoutResult>> {
        return vendorRepository.logout(accountId, accountType, accessToken, refreshToken, expiredAt)
    }

    override suspend fun updateJajan(
        token: String,
        id: String,
        category: String,
        name: String,
        price: Int,
        picture: String,
        jajanId: String
    ): Flow<Resource<AddJajanItemResult>> {
        return vendorRepository.updateJajan(
            token, id, category, name, price, picture, jajanId
        )
    }

    override suspend fun getJajanbyId(token: String, jajanId: String): Flow<Resource<Jajan>> {
        return vendorRepository.getJajanbyId(
            token, jajanId
        )
    }

    override suspend fun deleteJajanbyId(token: String, jajanId: String): Flow<Resource<String>> {
        return vendorRepository.deleteJajanbyId(
            token, jajanId
        )
    }
}