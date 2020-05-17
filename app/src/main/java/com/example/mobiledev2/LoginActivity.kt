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
import com.github.kittinunf.fuel.Fuel
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    var gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var loginButton = findViewById<Button>(R.id.loginButton);
        var usernameInput = findViewById<TextInputEditText>(R.id.usernameInput)
        var passwordInput = findViewById<TextInputEditText>(R.id.passwordInput)

        loginButton.setOnClickListener {
            var url = getString(R.string.URL)
            var sharedPrefs: SharedPreferences = getSharedPreferences(getString(R.string.shared_prefs), Context.MODE_PRIVATE)

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
                    Log.i("Response : " , token);

                    val projectActivity = Intent(this, ListOfProjectsActivity::class.java)

                    var editor = sharedPrefs.edit()
                    editor.putString("token",token)
                    editor.apply()

                    startActivity(projectActivity)
                },
                Response.ErrorListener { error -> Log.i("response", error.toString() ) })

            queue.add(stringRequest)
        }

    }

}
