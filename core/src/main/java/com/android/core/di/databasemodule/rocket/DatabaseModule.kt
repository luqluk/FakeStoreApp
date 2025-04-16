package com.android.core.di.databasemodule.rocket

import android.app.Application
import androidx.room.Room
import com.android.core.database.rocket.SpaceXLaunchesDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    // Mendeklarasikan sebuah singleton untuk SpaceXLaunchesDatabase
    single {
        provideDatabase(androidApplication()) // Memanggil fungsi untuk menyediakan instance database
    }
}

// Fungsi untuk membuat instance SpaceXLaunchesDatabase menggunakan Room
fun provideDatabase(application: Application): SpaceXLaunchesDatabase {
    return Room.databaseBuilder(
        application, // Context dari aplikasi, diberikan oleh Koin secara otomatis
        SpaceXLaunchesDatabase::class.java, // Class database yang sudah didefinisikan
        SpaceXLaunchesDatabase.DATABASE_NAME // Nama database yang akan dibuat
    ).build() // Membangun instance database
}