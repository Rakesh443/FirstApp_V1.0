package com.example.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val l=userId.text
        Toast.makeText(this,"Started $l",Toast.LENGTH_LONG).show()
        if(userId.text.isBlank() || passwordId.text.isBlank() ) loginbuttonId.isClickable = false
       // check()

    }

    private fun check() {
        Toast.makeText(this,"Started ${userId.text}",Toast.LENGTH_LONG).show()
        if(userId.text.isBlank() || passwordId.text.isBlank()) {
            Toast.makeText(this,"Ending",Toast.LENGTH_LONG).show()
            //loginbuttonId.isClickable = false
        }else{
            loginbuttonId.isClickable = true
            Toast.makeText(this,"Sucess",Toast.LENGTH_LONG).show()
        }

        TODO("Not yet implemented")
    }

    fun login(view: View) {

    }
}
