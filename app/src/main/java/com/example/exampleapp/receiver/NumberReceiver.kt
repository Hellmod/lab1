package com.example.exampleapp.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.exampleapp.services.SimpleService.Companion.EXTRAS_CLOCK_TICK
import com.example.exampleapp.services.SimpleService.Companion.EXTRAS_USER_NAME
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NumberReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("RMRM", "Received message")
        intent?.let {
            processReceivedIntent(it)
        }
    }

    private fun processReceivedIntent(intent: Intent) {
        val clockTick = intent.getIntExtra(EXTRAS_CLOCK_TICK, 0)
        val userName = intent.getStringExtra(EXTRAS_USER_NAME) ?: ""
        Log.d("RMRM", "NumberReceiver: userName=${userName}, clockTick=${clockTick}")

    }
    companion object {
        const val NUMBER_RECEIVER_ACTION = "NUMBER_RECEIVER_ACTION"
    }

}