package com.example.exampleapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "Mafia.db"
                )
                    .allowMainThreadQueries()//aaaa jak mogłeś to tu zostawić ;/
                    //.createFromAsset("database/init_mafia.db")
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}