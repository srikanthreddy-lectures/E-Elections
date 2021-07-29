package com.example.firebasedemoproject

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.core.Context
import java.util.*

class LanguageAvtivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadLanguage()

        setContentView(R.layout.activity_language_avtivity)

        var langList = arrayOf("English","हिंदी", "తెలుగు")

        val languageBtn=findViewById<Button>(R.id.language_btn)
        languageBtn.setOnClickListener(View.OnClickListener {

            var alertBox = AlertDialog.Builder(this)
            alertBox.setTitle("Choose Language")
            alertBox.setSingleChoiceItems(langList,-1){
                dialog, zz ->
                if (zz == 0){
                    setLocale("en")
                    recreate()
                }
                else if (zz == 1){
                    setLocale("hi")
                    recreate()
                }
                else if (zz == 2){
                    setLocale("te")
                    recreate()
                }
                dialog.dismiss()
            }

            alertBox.create().show()

        })


        val nextBtn=findViewById<Button>(R.id.next_btn)
        nextBtn.setOnClickListener(View.OnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        })
    }

    private fun loadLanguage(){
        val sharedPrefrence = getSharedPreferences("Settings", Activity.MODE_PRIVATE)
        var lang = sharedPrefrence.getString("lang","")
        setLocale(lang.toString())
        if(lang!=""){
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun setLocale(lang: String){
        var locale = Locale(lang)
        Locale.setDefault(locale)
        val congif = Configuration()
        congif.locale = locale
        baseContext.resources.updateConfiguration(congif,baseContext.resources.displayMetrics)

        val sharedPrefrenceEditor = getSharedPreferences("Settings", Activity.MODE_PRIVATE).edit()
        sharedPrefrenceEditor.putString("lang",lang)
        sharedPrefrenceEditor.apply()


    }

}

