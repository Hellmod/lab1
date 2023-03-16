package com.example.exampleapp.activities

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.exampleapp.ExampleApplication
import com.example.exampleapp.R
import com.example.exampleapp.receiver.NumberReceiver
import com.example.exampleapp.room.User
import com.example.exampleapp.services.SimpleService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HelloActivity : AppCompatActivity() {

    private val userNameText by lazy { findViewById<TextView>(R.id.myUserName) }
    private val startServiceBtn by lazy { findViewById<Button>(R.id.startServiceBtn) }
    private val stopServiceBtn by lazy { findViewById<Button>(R.id.stopServiceBtn) }
    private val readUsersBtn by lazy { findViewById<Button>(R.id.readUsersBtn) }
    private val myUserNameFromRoom by lazy { findViewById<TextView>(R.id.myUserNameFromRoom) }
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
        }

        stopServiceBtn.setOnClickListener {
            val intent = Intent(this, SimpleService::class.java)
            stopService(intent)
        }
        readUsersBtn.setOnClickListener {
            Log.d("MPMP", "readData")

            GlobalScope.launch {
                readAllData = ExampleApplication.database.userDao().getAll()

            }

            readAllData.forEach {
                Log.d("MPMP", "readAllData ${it}")
            }
            myUserNameFromRoom.text = readAllData.toString()
        }

        registerReceiver(numberReceiver, IntentFilter(NumberReceiver.NUMBER_RECEIVER_ACTION))
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(numberReceiver)
    }


}