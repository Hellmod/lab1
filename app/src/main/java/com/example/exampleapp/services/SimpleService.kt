package com.example.exampleapp.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.example.exampleapp.activities.HelloActivity
import com.example.exampleapp.receiver.NumberReceiver
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Thread.sleep

class SimpleService : Service() {

    var isDestroyed = false;

    companion object {
        val TAG = SimpleService.javaClass.canonicalName
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "Service created")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {


        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {

        super.onDestroy()
    }

}