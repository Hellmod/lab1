package com.example.exampleapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.exampleapp.ExampleApplication
import com.example.exampleapp.R
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private val userNameInput by lazy { findViewById<TextInputEditText>(R.id.userName) }
    private val userButton by lazy { findViewById<Button>(R.id.buttonName) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userButton.setOnClickListener {
            val userName = userNameInput.text.toString()

            val intent = Intent(this, HelloActivity::class.java)
            intent.putExtra(HelloActivity.USER_NAME_SERVICE, userName)
            startActivity(intent)

        }
    }
}