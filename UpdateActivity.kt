package com.idekhail.an2_sqlite2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class UpdateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        val update = findViewById<Button>(R.id.update)
        val delete = findViewById<Button>(R.id.delete)
        val back = findViewById<Button>(R.id.back)

        val uid = findViewById<TextView>(R.id.uid)
        val enterName = findViewById<EditText>(R.id.enterName)
        val enterAge = findViewById<EditText>(R.id.enterAge)

        val name = intent.getStringExtra("name").toString()
        val age = intent.getStringExtra("age").toString()

        val db = DBHelper(this, null)

        var cursor = db.login(name, age)

        if(cursor.moveToFirst()){
            uid.setText(cursor.getString(0)).toString()
            enterName.setText(cursor.getString(1).toString())
            enterAge.setText(cursor.getString(2))
        }

        update.setOnClickListener {
            val _id = cursor.getString(0).toInt()
            var response = db.updateName(_id, enterName.text.toString().trim(), enterAge.text.toString().trim())
            if(response > 0 ) {
                var i = Intent(this, LoginActivity::class.java)
                startActivity(i)
            }else{
                Toast.makeText(this, "Error!!", Toast.LENGTH_LONG).show()
            }
        }

        delete.setOnClickListener {
            val _id = cursor.getString(0).toInt()
            val response = db.deleteName(_id)

            if(response > 0) {
                val i = Intent(this, MainActivity::class.java)
                startActivity(i)
            }else{
                Toast.makeText(this, "Delete is Error!!", Toast.LENGTH_LONG).show()
            }
        }
        back.setOnClickListener {
            val  i = Intent(this, DisplayActivity::class.java)
            i.putExtra("name", name)
            i.putExtra("age", age)
            startActivity(i)
        }
    }
}