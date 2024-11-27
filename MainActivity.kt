package com.idekhail.an2_sqlite2

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val login = findViewById<Button>(R.id.login)
        val create = findViewById<Button>(R.id.create)
        val close = findViewById<Button>(R.id.close)



        login.setOnClickListener {
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
        }
        create.setOnClickListener {
            val i = Intent(this, CreateActivity::class.java)
            startActivity(i)
        }

        close.setOnClickListener {
            exitProcess(0)
        }
    }
}