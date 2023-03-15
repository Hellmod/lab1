package com.example.exampleapp.services

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import com.example.exampleapp.activities.HelloActivity
import com.example.exampleapp.receiver.NumberReceiver

class SimpleService : Service() {

    companion object {

        const val EXTRAS_CLOCK_TICK = "EXTRAS_CLOCK_TICK"
        const val EXTRAS_USER_NAME = "EXTRAS_USER_NAME"
    }

    private var isServiceRunning = false
    private var number = 0
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var runnable: Runnable
    var userName = ""

    override fun onCreate() {
        super.onCreate()
        Log.d("RMRM", "Service created")
        isServiceRunning = true
        startClock()
    }

    private fun startClock() {
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
        userName = intent?.getStringExtra(HelloActivity.USER_NAME_SERVICE) ?: ""
        sendUserNameAndClockTick()
        return START_STICKY
    }

    private fun sendUserNameAndClockTick() {
/*        val intent = Intent(this, NumberReceiver::class.java)
        intent.putExtra(HelloActivity.USER_NAME_SERVICE, userName)
        startActivity(intent)*/

        val newIntent = Intent(NumberReceiver.NUMBER_RECEIVER_ACTION).also {
            it.putExtra(EXTRAS_CLOCK_TICK, number)
            it.putExtra(
                EXTRAS_USER_NAME,
                userName
            )
        }
        sendBroadcast(newIntent)
    }

    override fun onDestroy() {
        super.onDestroy()
        isServiceRunning = false
        handler.removeCallbacks(runnable)
        Log.d("RMRM", "Service destroyed")
        sendUserNameAndClockTick()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

}