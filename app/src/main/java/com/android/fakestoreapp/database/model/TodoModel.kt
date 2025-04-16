package com.android.fakestoreapp.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

//Entity / Model
@Entity
data class TodoModel(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var title: String,
    var description: String,
    var dateTime: String,

)
