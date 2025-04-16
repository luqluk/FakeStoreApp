package com.android.fakestoreapp.database.query

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.android.fakestoreapp.database.model.TodoModel

@Dao
interface TodoDao {
    @Insert
    fun insert(todo: TodoModel): Long

    @Update
    fun update(todo: TodoModel): Int

    @Delete
    fun delete(todo: TodoModel): Int

    @Query("SELECT * FROM TodoModel ORDER BY dateTime")
    fun getAll(): List<TodoModel>

    @Query("SELECT * FROM TodoModel WHERE id = :id")
    fun getById(id: Long): TodoModel
}