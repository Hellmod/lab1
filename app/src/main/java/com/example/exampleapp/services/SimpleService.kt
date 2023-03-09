package com.example.exampleapp.services

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log

class SimpleService : Service() {

    private var isServiceRunning = false
    private var number = 0
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var runnable: Runnable

    override fun onCreate() {
        super.onCreate()
        Log.d("RMRM", "Service created")
        isServiceRunning = true
        runnable = Runnable {
            number++
            Log.d("RMRM", "Number: $number")
            if (isServiceRunning) {
                handler.postDelayed(runnable, 1000)
            }
        }
        handler.postDelayed(runnable, 1000)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("RMRM", "Service started")
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        isServiceRunning = false
        handler.removeCallbacks(runnable)
        Log.d("RMRM", "Service destroyed")
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

}