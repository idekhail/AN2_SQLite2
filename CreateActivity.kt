package com.idekhail.an2_sqlite2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class CreateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        val enterName = findViewById<EditText>(R.id.enterName)
        val enterAge = findViewById<EditText>(R.id.enterAge)

        val create= findViewById<Button>(R.id.create)
        val cancel = findViewById<Button>(R.id.cancel)

// Create User
        create.setOnClickListener {
            if(enterName.text.toString().isEmpty() || enterAge.text.toString().isEmpty()){
                Toast.makeText(this, "fields cannot be empty", Toast.LENGTH_SHORT).show()
            }else{
                val db = DBHelper(this, null)

                var response = db.addName(enterName.text.toString().trim(), enterAge.text.toString().trim())
                if(response > 0 ) {
                    var i = Intent(this, MainActivity::class.java)
                    startActivity(i)
                }else{
                    Toast.makeText(this, "Error!!", Toast.LENGTH_LONG).show()
                }
            }
        }
// Back Home
        cancel.setOnClickListener{
            var i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
    }
}