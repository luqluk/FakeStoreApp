package com.android.core.domain.repository

import com.android.core.database.product.ProductEntity


interface LocalRepository {
    suspend fun getAll(): List<ProductEntity>

    suspend fun getById(id: Int): ProductEntity?

    suspend fun insert(productEntity: ProductEntity)

    suspend fun insertAll(listOfLaunchInfoEntities: List<ProductEntity>)

    suspend fun update(productEntity: ProductEntity)

    suspend fun delete(productEntity: ProductEntity)

    suspend fun deleteAll()
}