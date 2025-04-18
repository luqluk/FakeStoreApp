package com.android.core.di.databasemodule.product

import android.content.Context
import androidx.room.Room
import com.android.core.database.product.ProductsDao
import com.android.core.database.product.ProductDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        provideDatabase(androidContext()) // Memanggil fungsi untuk menyediakan instance database
    }
    single { provideUserDao(get()) }
    single { androidContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE) }

}

fun provideDatabase(context: Context): ProductDatabase {
    return Room.databaseBuilder(
        context, // Context dari aplikasi, diberikan oleh Koin secara otomatis
        ProductDatabase::class.java, // Class database yang sudah didefinisikan
        ProductDatabase.DATABASE_NAME // Nama database yang akan dibuat
    )
        .fallbackToDestructiveMigration()
        .build() // Membangun instance database
}

private fun provideUserDao(appDatabase: ProductDatabase): ProductsDao {
    return appDatabase.dao()
}
