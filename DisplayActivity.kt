package com.idekhail.an2_sqlite2

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class DisplayActivity : AppCompatActivity() {

    lateinit var name1: String
    lateinit var age1: String

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)


        val logout = findViewById<Button>(R.id.logout)
        val search = findViewById<Button>(R.id.search)
        val update = findViewById<Button>(R.id.update)
        val print = findViewById<Button>(R.id.print)

        val Id = findViewById<TextView>(R.id.Id)
        val Name = findViewById<TextView>(R.id.Name)
        val Age = findViewById<TextView>(R.id.Age)

        val uid = findViewById<TextView>(R.id.uid)
        val name = findViewById<TextView>(R.id.name)
        val age = findViewById<TextView>(R.id.age)
        val myAge = findViewById<EditText>(R.id.myAge)

        name1 = intent.getStringExtra("name").toString()
        age1 = intent.getStringExtra("age").toString()

        val db = DBHelper(this, null)

        var cursor = db.login(name1, age1)

        if(cursor.moveToFirst()){
            uid.setText(cursor.getString(0)).toString()
            name.setText(cursor.getString(1))
            age.setText(cursor.getString(2))
        }
        
        logout.setOnClickListener {
            cursor.close()
            db.close()
            val  i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }

        update.setOnClickListener {
            val  i = Intent(this, UpdateActivity::class.java)
            i.putExtra("name", name1)
            i.putExtra("age", age1)
            startActivity(i)
        }

        search.setOnClickListener{
            Id.setText("Id\n---------\n")
            Name.setText("Name\n---------\n")
            Age.setText("Age\n---------\n")
            // creating an object of DBHelper class

            val db = DBHelper(this, null)

            // Calling method to get all names from our database
            val cursor = db.getAllByAge(myAge.text.toString())
            //Toast.makeText(this, " $cursor", Toast.LENGTH_LONG).show()
            if (cursor!!.moveToFirst()) {
                Id.append(cursor.getString(0) + "\n")
                Name.append(cursor.getString(1) + "\n")
                Age.append(cursor.getString(2) + "\n")

                // moving our cursor to next position
                while (cursor.moveToNext()) {
                    Id.append(cursor.getString(0) + "\n")
                    Name.append(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl)) + "\n")
                    Age.append(cursor.getString(cursor.getColumnIndex(DBHelper.AGE_COL)) + "\n")
                }
            }
            // clearing edit texts
            myAge.text.clear()

            // close our cursor
            cursor!!.close()
        }

        print.setOnClickListener{
            Id.setText("Id\n---------\n")
            Name.setText("Name\n---------\n")
            Age.setText("Age\n---------\n")
            // creating an object of DBHelper class
            val db = DBHelper(this, null)

            // Calling method to get all names from our database
            val cursor = db.getAll()
            //Toast.makeText(this, " $cursor", Toast.LENGTH_LONG).show()
            if (cursor!!.moveToFirst()) {
                Id.append(cursor.getString(0) + "\n")
                Name.append(cursor.getString(1) + "\n")
                Age.append(cursor.getString(2) + "\n")

                // moving our cursor to next position
                while (cursor.moveToNext()) {
                    Id.append(cursor.getString(0) + "\n")
                    Name.append(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl)) + "\n")
                    Age.append(cursor.getString(cursor.getColumnIndex(DBHelper.AGE_COL)) + "\n")
                }
            }
            // close our cursor
            cursor!!.close()
        }
    }
}