package com.example.movielogin.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.sax.StartElementListener
import android.util.Log
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.movielogin.MainActivity
import com.example.movielogin.R


class LoginActivity : AppCompatActivity() {


    lateinit var username_edit : EditText
    lateinit var password_edit: EditText
    lateinit var signInBtn : Button
    final val loginPref = "login_preferences"
    val activity_name = "LoginActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var pref = getSharedPreferences(loginPref, Context.MODE_PRIVATE)

        username_edit = findViewById(R.id.edit_username)
        password_edit = findViewById(R.id.password_edit)
        signInBtn = findViewById(R.id.signinBtn)

        signInBtn.setOnClickListener {
            Log.d(activity_name, "button pressed")
            val username = username_edit.text.toString()
            val password = password_edit.text.toString()
            if (pref.contains(username)) {
                val ps = pref.getString(username, "")
                if (ps == password) {
                    LogOn();
                }
                else {
                    val toast = Toast.makeText(
                        applicationContext,
                        "Wrong password",
                        Toast.LENGTH_SHORT
                    )
                    toast.show()
                }
            }
            else {
                Toast.makeText(applicationContext, "Created a new account", Toast.LENGTH_SHORT).show()
                with(pref.edit()) {
                    putString(username, password)
                    commit()
                }
                LogOn()
            }
        }
    }

    private fun LogOn() {
        Toast.makeText(applicationContext,"Loggin in..", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MainActivity::class.java)
        finish()
        startActivity(intent)
    }
}
