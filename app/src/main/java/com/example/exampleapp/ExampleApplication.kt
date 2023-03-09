package com.example.exampleapp

import android.app.Application
import androidx.room.Room
import com.example.exampleapp.room.AppDatabase

class ExampleApplication : Application() {

    val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "example-database"
        ).build()
    }
}