package com.example.exampleapp.activities

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.exampleapp.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HelloActivity : AppCompatActivity() {

    private val userNameText by lazy { findViewById<TextView>(R.id.myUserName) }
    private val startServiceBtn by lazy { findViewById<Button>(R.id.startServiceBtn) }
    private val stopServiceBtn by lazy { findViewById<Button>(R.id.stopServiceBtn) }

    companion object {

        const val USER_NAME_EXTRA = "USER_NAME"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hello)

        val userName = intent.getStringExtra(USER_NAME_EXTRA)
        userNameText.text = userName
        var isDestroyed = false
        startServiceBtn.setOnClickListener {

            isDestroyed = false
            GlobalScope.launch {
                var number = 0;
                while (!isDestroyed) {
                    number++;
                    Log.d("RMRM", "New number $number");
                    delay(1000);
                }
            }

        }

        stopServiceBtn.setOnClickListener {
            isDestroyed = true
        }

    }

    override fun onDestroy() {
        super.onDestroy()
    }

}