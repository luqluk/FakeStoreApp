package com.android.fakestoreapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.fakestoreapp.database.model.TodoModel
import com.android.fakestoreapp.database.query.TodoDao

@Database(entities = [TodoModel::class], version = 1,exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun todoDao(): TodoDao

    companion object {
        private const val DB_NAME = "todo.db"

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DB_NAME
                )
                    .allowMainThreadQueries() // Memungkinkan query di main thread
                    .build().also { instance = it }
            }
        }
    }
}
