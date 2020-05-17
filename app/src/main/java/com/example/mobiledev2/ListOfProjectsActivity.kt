package com.example.mobiledev2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.mobiledev2.adapters.ProjectsAdapter
import com.example.mobiledev2.model.LoginData
import com.example.mobiledev2.model.ProjectData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

class ListOfProjectsActivity: AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_projects)
        var sharedPrefs = getSharedPreferences(getString(R.string.shared_prefs), Context.MODE_PRIVATE)

        var token = sharedPrefs.getString("token","no token")

        loadProjects(token)
    }

    fun loadProjects(token: String){
        var url = getString(R.string.URL)
        var gson = Gson()

        var recyclerView: RecyclerView = findViewById(R.id.projectList)

        recyclerView.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)

        val queue = Volley.newRequestQueue(this)
        val stringRequest = object : StringRequest(Request.Method.GET, "${url}/projects"   ,
            Response.Listener { response ->
                val projectData : ArrayList<ProjectData> = gson.fromJson(response, object : TypeToken<ArrayList<ProjectData>>() {}.type)
                var adapter = ProjectsAdapter(projectData,this)

                recyclerView.adapter = adapter
            },
            Response.ErrorListener {  }
        ){
            override fun getHeaders(): Map<String, String> {
                // Create HashMap of your Headers as the example provided below

                val headers = HashMap<String, String>()
                headers["Authorization"] = "Bearer $token"
                return headers
            }
        }

        queue.add(stringRequest)
    }
}