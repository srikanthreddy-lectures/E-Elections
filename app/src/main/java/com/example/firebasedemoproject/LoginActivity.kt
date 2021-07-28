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

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val registerBtn = findViewById<Button>(R.id.login_register_btn)

        registerBtn.setOnClickListener(View.OnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        })

        val email = findViewById<EditText>(R.id.login_email_ed)
        val password = findViewById<EditText>(R.id.login_password_ed)
        val loginBtn = findViewById<Button>(R.id.login_btn)

        loginBtn.setOnClickListener(View.OnClickListener {

            auth = Firebase.auth

            auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.i("---->", "signInWithEmail:success")
                        val user = auth.currentUser
                        Log.i("---->", "My UID is : ${user?.uid}")
                        val intent = Intent(this,MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.i("---->", "signInWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }


        })

    }
}