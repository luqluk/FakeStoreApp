package com.android.core.database.product

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ProductsDao {
    @Query("SELECT * FROM ProductEntity")
    suspend fun getAll(): List<ProductEntity>

    @Query("SELECT * FROM ProductEntity WHERE id = :id")
    suspend fun getById(id: Int): ProductEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(productEntity: ProductEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(listOfLaunchInfoEntities: List<ProductEntity>)

    @Update
    suspend fun update(productEntity: ProductEntity)

    @Delete
    suspend fun delete(productEntity: ProductEntity)

    @Query("DELETE FROM ProductEntity")
    suspend fun deleteAll()
}
