package com.android.core.database.product

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ProductEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun dao(): ProductsDao

    companion object {
        const val DATABASE_NAME = "product_db"
    }
}
