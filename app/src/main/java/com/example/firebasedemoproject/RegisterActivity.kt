package com.example.firebasedemoproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val loginBtn = findViewById<Button>(R.id.register_login_btn)

        loginBtn.setOnClickListener(View.OnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        })

        val email = findViewById<EditText>(R.id.register_email_ed)
        val password = findViewById<EditText>(R.id.register_password_ed)
        val registerBtn = findViewById<Button>(R.id.register_btn)

        registerBtn.setOnClickListener(View.OnClickListener {

            auth = Firebase.auth

            auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.i("---->", "createUserWithEmail:success")
                        val user = auth.currentUser
                        Log.i("---->", "My UID is : ${user?.uid}")
                        //val user = auth.currentUser
                        val intent = Intent(this,LoginActivity::class.java)
                        startActivity(intent)

                    } else {
                        // If sign in fails, display a message to the user.
                        Log.i("---->", "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }

        })

    }
}