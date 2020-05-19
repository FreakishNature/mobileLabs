package com.example.mobiledev2.presenters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.mobiledev2.ListOfProjectsActivity
import com.example.mobiledev2.R
import com.example.mobiledev2.model.LoginData
import com.google.gson.Gson
import org.json.JSONObject

class LoginPresenter(val activity: Activity) {
    var gson = Gson()

    fun login(username: String, password: String){
        var url = activity.getString(R.string.URL)

        var body = gson.toJson(
            LoginData(
                username,
                password)
        )

        val queue = Volley.newRequestQueue(activity)
        val stringRequest = JsonObjectRequest(
            Request.Method.POST, "${url}/login", JSONObject(body),
            Response.Listener<JSONObject> { response ->
                val token = response.get("token").toString()
                Log.i("Response : " , token);

                val projectActivity = Intent(activity, ListOfProjectsActivity::class.java)

                var editor = activity.getSharedPreferences(activity.getString(R.string.shared_prefs), Context.MODE_PRIVATE).edit()
                editor.putString("token",token)
                editor.apply()

                activity.startActivity(projectActivity)
            },
            Response.ErrorListener { error -> Log.i("response", error.toString() ) })

        queue.add(stringRequest)
    }
}