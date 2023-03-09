package com.example.exampleapp.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.exampleapp.ExampleApplication
import com.example.exampleapp.room.User
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NumberReceiver : BroadcastReceiver() {
    companion object {
        const val NUMBER_EXTRA = "number"
        const val USER_NAME_EXTRA = "userName"
        const val NUMBER_RECEIVER_ACTION = "NUMBER_RECEIVER_ACTION"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("NumberReceiver", "Received message")
    }

}