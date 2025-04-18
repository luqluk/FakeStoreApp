package com.android.core.data.repository

import com.android.core.database.product.ProductsDao
import com.android.core.database.product.ProductEntity
import com.android.core.domain.repository.LocalRepository

class LocalRepositoryImpl(
    private val dao: ProductsDao
): LocalRepository {
    override suspend fun getAll(): List<ProductEntity> {
        return dao.getAll()
    }

    override suspend fun getById(id: Int): ProductEntity? {
        return dao.getById(id)
    }

    override suspend fun insert(productEntity: ProductEntity) {
        dao.insert(productEntity)
    }

    override suspend fun insertAll(listOfLaunchInfoEntities: List<ProductEntity>) {
        dao.insertAll(listOfLaunchInfoEntities)
    }

    override suspend fun update(productEntity: ProductEntity) {
        dao.update(productEntity)
    }

    override suspend fun delete(productEntity: ProductEntity) {
        dao.delete(productEntity)
    }

    override suspend fun deleteAll() {
        dao.deleteAll()
    }
}