package com.example.firstapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences

    var isRemembered=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPreferences=getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        isRemembered = sharedPreferences.getBoolean("Check",false)
        val nam:String=nameId.text.toString()
        val pas:String=passId.text.toString()


        if(isRemembered){
            val  intent=Intent(this, AfterLoginActivity::class.java)
            startActivity(intent)
            finish()
        }




        logId.setOnClickListener {
            val name:String=nameId.text.toString()

            val editor:SharedPreferences.Editor=sharedPreferences.edit()
            editor.putString("Name",name)
            editor.putBoolean("Check",true)
            editor.apply()
            val  intent=Intent(this, MusicActivity::class.java)
            startActivity(intent)

        }

    }


    private val textWatcher=object :TextWatcher{
        override fun afterTextChanged(p0: Editable?) {
            TODO("Not yet implemented")
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            TODO("Not yet implemented")
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            TODO("Not yet implemented")
           // if()
        }

    }

}