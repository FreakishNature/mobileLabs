package com.example.mobiledev2

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.mobiledev2.model.LoginData
import com.example.mobiledev2.presenters.LoginPresenter
import com.github.kittinunf.fuel.Fuel
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    val loginPresenter = LoginPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var loginButton = findViewById<Button>(R.id.loginButton);
        var usernameInput = findViewById<TextInputEditText>(R.id.usernameInput)
        var passwordInput = findViewById<TextInputEditText>(R.id.passwordInput)

        loginButton.setOnClickListener {
           loginPresenter.login(usernameInput.text.toString(), passwordInput.text.toString())

        }

    }

}
