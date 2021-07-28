package com.example.firebasedemoproject

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import java.util.*

class LanguageAvtivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language_avtivity)

        var langList = arrayOf("English","हिंदी", "తెలుగు")

        val languageBtn=findViewById<Button>(R.id.language_btn)
        languageBtn.setOnClickListener(View.OnClickListener {

            var alertBox = AlertDialog.Builder(this)
            alertBox.setTitle("Choose Language")
            alertBox.setSingleChoiceItems(langList,-1){
                dialog, zz ->
                if (zz == 0){
                    var locale = Locale("en")
                    Locale.setDefault(locale)

                    val congif = Configuration()
                    congif.locale = locale
                    baseContext.resources.updateConfiguration(congif,baseContext.resources.displayMetrics)

                    recreate()
                }
                else if (zz == 1){
                    var locale = Locale("hi")
                    Locale.setDefault(locale)

                    val congif = Configuration()
                    congif.locale = locale
                    baseContext.resources.updateConfiguration(congif,baseContext.resources.displayMetrics)



                    recreate()
                }
                else if (zz == 2){
                    var locale = Locale("te")
                    Locale.setDefault(locale)

                    val congif = Configuration()
                    congif.locale = locale
                    baseContext.resources.updateConfiguration(congif,baseContext.resources.displayMetrics)

                    recreate()
                }
                dialog.dismiss()
            }

            alertBox.create().show()

        })
    }
}