package com.example.firstapp

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class AfterLoginActivity : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_after_login)

        sharedPreferences=getSharedPreferences("SHARED_PREF",Context.MODE_PRIVATE)
    }

    fun out(view: View) {
        val editor:SharedPreferences.Editor=sharedPreferences.edit()

        editor.putBoolean("Check",false)
        editor.apply()
        finish()
    }

    override fun onBackPressed() {
        Toast.makeText(applicationContext,"Logout",Toast.LENGTH_LONG).show()
    }
}