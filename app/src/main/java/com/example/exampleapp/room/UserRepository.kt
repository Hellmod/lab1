package com.example.exampleapp.room

import android.database.Cursor
import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {

    val readAllData: List<User> = userDao.getAll()
    val readCursor: Cursor = userDao.getAllCursor()

    fun addUser(user: User){
        userDao.insert(user)
    }
}