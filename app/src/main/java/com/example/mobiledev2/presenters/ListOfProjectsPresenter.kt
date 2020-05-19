package com.example.mobiledev2.presenters

import android.app.Activity
import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.mobiledev2.R
import com.example.mobiledev2.adapters.ProjectsAdapter
import com.example.mobiledev2.model.ProjectData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListOfProjectsPresenter(val activity: Activity) {
    var gson = Gson()

    fun loadProjects(recyclerView: RecyclerView){
        var sharedPrefs = activity.getSharedPreferences(
            activity.getString(R.string.shared_prefs),
            Context.MODE_PRIVATE
        )

        var token = sharedPrefs.getString("token","no token")

        var url = activity.getString(R.string.URL)

        val queue = Volley.newRequestQueue(activity)
        val stringRequest = object : StringRequest(
            Request.Method.GET, "${url}/projects"   ,
            Response.Listener { response ->
                val projectData : ArrayList<ProjectData> = gson.fromJson(
                    response,
                    object : TypeToken<ArrayList<ProjectData>>() {}.type
                )
                val adapter = ProjectsAdapter(projectData,activity)

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