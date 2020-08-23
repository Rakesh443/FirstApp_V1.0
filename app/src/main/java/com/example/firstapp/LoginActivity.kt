package com.example.firstapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

   // lateinit var sharedPreferences: SharedPreferences

   // var isRemembered=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //sharedPreferences=getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
       // isRemembered = sharedPreferences.getBoolean("Check",false)
//        logId.setClickable(false)
       // check()

//        if(isRemembered){
//            val  intent=Intent(this, AfterLoginActivity::class.java)
//            startActivity(intent)
//            finish()
//        }

    }

    fun login(view: View) {
        val name:String=nameId.text.toString()
        val pas:String=passId.text.toString()
//        val editor:SharedPreferences.Editor=sharedPreferences.edit()
//        editor.putString("Name",name)
//        editor.putBoolean("Check",true)
//        editor.apply()
        if(name.equals(pas.reversed()) && !name.isNullOrBlank()){
        val  intent=Intent(this, MusicActivity::class.java)
        startActivity(intent)}
        else{
            Toast.makeText(applicationContext,"Invalid UserName and Password, Contact Admin",Toast.LENGTH_LONG).show()
        }
    }


}