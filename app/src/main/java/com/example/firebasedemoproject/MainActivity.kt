package com.example.firebasedemoproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Write a message to the database
        val database = Firebase.database.reference

        var add_btn = findViewById<Button>(R.id.add_btn)
        add_btn.setOnClickListener(View.OnClickListener {
            var id = findViewById<EditText>(R.id.id_tv)
            var name = findViewById<EditText>(R.id.name_tv)
            var age = findViewById<EditText>(R.id.age_tv)

            var user:User= User(name.text.toString(),age.text.toString().toInt())

            database.child(id.text.toString()).setValue(user)

        })

        var result = findViewById<TextView>(R.id.result)

        var getdata = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var sb = StringBuilder()
                for (i in snapshot.children){
                    var rname=i.child("name").getValue()
                    var rage=i.child("age").getValue()
                    sb.append("${i.key} $rname $rage \n")
                }
                result.text = sb
            }

            override fun onCancelled(error: DatabaseError) {

            }
        }

        database.addValueEventListener(getdata)
        database.addListenerForSingleValueEvent(getdata)

        var logout = findViewById<Button>(R.id.logout_btn)
        logout.setOnClickListener(View.OnClickListener {

            Firebase.auth.signOut()

            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        })

    }
}