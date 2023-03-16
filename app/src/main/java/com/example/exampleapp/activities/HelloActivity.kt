package com.example.exampleapp.activities

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import com.example.exampleapp.ExampleApplication
import com.example.exampleapp.R
import com.example.exampleapp.receiver.NumberReceiver
import com.example.exampleapp.room.AppDatabase
import com.example.exampleapp.room.User
import com.example.exampleapp.room.UserRepository
import com.example.exampleapp.services.SimpleService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HelloActivity : AppCompatActivity() {

    private val userNameText by lazy { findViewById<TextView>(R.id.myUserName) }
    private val startServiceBtn by lazy { findViewById<Button>(R.id.startServiceBtn) }
    private val stopServiceBtn by lazy { findViewById<Button>(R.id.stopServiceBtn) }
    private val readUsersBtn by lazy { findViewById<Button>(R.id.readUsersBtn) }
    private val myUserNameFromRoom by lazy { findViewById<TextView>(R.id.myUserNameFromRoom) }
    var isDestroyedService = false
    var readAllData: List<User> = listOf()

    private val numberReceiver = NumberReceiver()

    companion object {

        const val USER_NAME_SERVICE = "USER_NAME_SERVICE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hello)

        val userName = intent.getStringExtra(USER_NAME_SERVICE)
        userNameText.text = userName

        startServiceBtn.setOnClickListener {
            val intent = Intent(this, SimpleService::class.java)
            intent.putExtra(USER_NAME_SERVICE, userName)
            startService(intent)
            //runOldTimer()
        }

        stopServiceBtn.setOnClickListener {
            val intent = Intent(this, SimpleService::class.java)
            stopService(intent)
            //stopOldTime()
        }
        readUsersBtn.setOnClickListener {
            Log.d("RMRM2", "setOnClickListener")

            GlobalScope.launch {
                readAllData = ExampleApplication.database.userDao().getAll()

            }

            readAllData.forEach {
                Log.d("RMRM2", "readAllData ${it}")
            }
            myUserNameFromRoom.text = readAllData.toString()

        }

        registerReceiver(numberReceiver, IntentFilter(NumberReceiver.NUMBER_RECEIVER_ACTION))

    }

    fun runOldTimer() {
        isDestroyedService = false
        GlobalScope.launch {
            var number = 0;
            while (!isDestroyedService) {
                number++;
                Log.d("RMRM", "New number $number");
                delay(1000);
            }
        }
    }

    fun stopOldTime() {
        isDestroyedService = true
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(numberReceiver)
    }


}