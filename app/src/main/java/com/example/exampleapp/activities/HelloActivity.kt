package com.example.exampleapp.activities

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.exampleapp.ExampleApplication
import com.example.exampleapp.R
import com.example.exampleapp.receiver.NumberReceiver
import com.example.exampleapp.receiver.NumberReceiver.Companion.NUMBER_RECEIVER_ACTION
import com.example.exampleapp.services.SimpleService
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HelloActivity : AppCompatActivity() {

    private val userNameText by lazy { findViewById<TextView>(R.id.myUserName) }

    companion object {
        const val USER_NAME_EXTRA = "USER_NAME"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hello)

        val userName = intent.getStringExtra(USER_NAME_EXTRA)
        userNameText.text = userName

    }

    override fun onDestroy() {
        super.onDestroy()
    }

}