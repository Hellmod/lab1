package com.example.exampleapp

import android.app.Application
import androidx.room.Room
import com.example.exampleapp.room.AppDatabase

class ExampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "example-database"
        ).build()
    }

    companion object {
        lateinit var database: AppDatabase
    }
}