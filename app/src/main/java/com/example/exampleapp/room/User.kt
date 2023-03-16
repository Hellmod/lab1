package com.example.exampleapp.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val uid: Int? = null,
    @ColumnInfo(name = "user_mame") val userName: String?,
    @ColumnInfo(name = "number") val number: Int?
)
