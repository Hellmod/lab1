package com.example.exampleapp.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.exampleapp.ExampleApplication
import com.example.exampleapp.room.User
import com.example.exampleapp.services.SimpleService.Companion.EXTRAS_CLOCK_TICK
import com.example.exampleapp.services.SimpleService.Companion.EXTRAS_USER_NAME
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NumberReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("MPMP", "Received message")
        intent?.let {
            processReceivedIntent(it)
        }
    }

    private fun processReceivedIntent(intent: Intent) {
        val clockTick = intent.getIntExtra(EXTRAS_CLOCK_TICK, 0)
        val userName = intent.getStringExtra(EXTRAS_USER_NAME) ?: ""
        Log.d("MPMP", "NumberReceiver: userName=${userName}, clockTick=${clockTick}")
        saveUserAndClockTick(userName, clockTick)
    }

    companion object {
        const val NUMBER_RECEIVER_ACTION = "NUMBER_RECEIVER_ACTION"
    }

    private fun saveUserAndClockTick(userName: String, clockTick: Int) {
        Log.d("MPMP", "saveUserAndClockTick")
        GlobalScope.launch {
            ExampleApplication.database.userDao().insert(
                User(userName = userName, number = clockTick)
            )
        }
    }
}