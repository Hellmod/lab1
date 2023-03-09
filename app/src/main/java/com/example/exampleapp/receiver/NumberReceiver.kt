package com.example.exampleapp.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class NumberReceiver : BroadcastReceiver() {
    companion object {

        const val NUMBER_EXTRA = "number"
        const val USER_NAME_EXTRA = "userName"
        const val NUMBER_RECEIVER_ACTION = "NUMBER_RECEIVER_ACTION"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("RMRM", "Received message")
        val number = intent?.getIntExtra(NUMBER_EXTRA, 0) ?: 0
        val user = intent?.getStringExtra(USER_NAME_EXTRA) ?: ""
        Log.d("RMRM", "User: $user, Number: $number")
    }

}