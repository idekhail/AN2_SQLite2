package com.idekhail.an2_sqlite2

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val login = findViewById<Button>(R.id.login)
        val cancel = findViewById<Button>(R.id.cancel)

        val enterName = findViewById<EditText>(R.id.enterName)
        val enterAge = findViewById<EditText>(R.id.enterAge)

        login.setOnClickListener {
            var cursor : Cursor
            val db = DBHelper(this, null)

            if (enterName.text.isNullOrEmpty() && enterAge.text.isNullOrEmpty())
                Toast.makeText(this, " Name && Age is Empty", Toast.LENGTH_LONG).show()
            else {
                val name = enterName.text.toString()
                val age = enterAge.text.toString()
                cursor = db.login(name, age)
                if (cursor.count > 0) {

                    enterName.text.clear()
                    enterAge.text.clear()
                    cursor.close()

                    val  i = Intent(this, DisplayActivity::class.java)
                    i.putExtra("name", name)
                    i.putExtra("age", age)
                    startActivity(i)
                }else
                    Toast.makeText(this, " Name is Error", Toast.LENGTH_LONG).show()
            }
        }

        cancel.setOnClickListener {
            val  i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
    }
}
