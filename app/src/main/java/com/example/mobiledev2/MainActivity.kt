package com.example.mobiledev2

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
import com.github.kittinunf.fuel.Fuel
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    var gson = Gson()
    var url = "http://192.168.0.24:8081"
//    lateinit var token : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var loginButton = findViewById<Button>(R.id.loginButton);
        var usernameInput = findViewById<TextInputEditText>(R.id.usernameInput)
        var passwordInput = findViewById<TextInputEditText>(R.id.passwordInput)

        loginButton.setOnClickListener {

            var body = gson.toJson(
                LoginData(
                    usernameInput.text.toString(),
                    passwordInput.text.toString())
            )

            Log.i("Body",body)

            val queue = Volley.newRequestQueue(this)
            val stringRequest = JsonObjectRequest(
                Request.Method.POST, "${url}/login",JSONObject(body),
                Response.Listener<JSONObject> { response ->
                    val token = response.get("token").toString()

                },
                Response.ErrorListener { error -> Log.i("response", error.toString() ) })


            queue.add(stringRequest)
        }

    }

}
