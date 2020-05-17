package com.example.mobiledev2.adapters

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.mobiledev2.ProjectActivity
import com.example.mobiledev2.R
import com.example.mobiledev2.model.ProjectData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProjectsAdapter(private val projectList: ArrayList<ProjectData>, private val context: Context) :
    RecyclerView.Adapter<ProjectsAdapter.ViewHolder>() {
    var gson = Gson()
    var MAX_LENGTH = 120
    class ViewHolder(item: View): RecyclerView.ViewHolder(item){
        var title: TextView = item.findViewById(R.id.title)
        var description: TextView = item.findViewById(R.id.desc)
        var detailedButton: TextView = item.findViewById(R.id.detailedButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.project_demo,parent,false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return projectList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var project: ProjectData = projectList[position]
        holder.title.text = project.name
        if(project.description.length > MAX_LENGTH){
            holder.description.text = "${project.description.substring(MAX_LENGTH)}..."
        } else {
            holder.description.text = project.description
        }

        holder.detailedButton.setOnClickListener{
            loadProject(project.name)
        }
    }

    fun loadProject(projectName: String){
        var url = context.getString(R.string.URL)
        var sharedPreferences = context.getSharedPreferences(context.getString(R.string.shared_prefs),Context.MODE_PRIVATE)

        val queue = Volley.newRequestQueue(context)
        val stringRequest = object : StringRequest(
            Request.Method.GET, "${url}/projects/${projectName}"   ,
            Response.Listener { response ->
                val projectData = gson.fromJson(response, ProjectData::class.java)

                var projectActivityIntent = Intent(context,ProjectActivity::class.java)
                projectActivityIntent.putExtra("title",projectData.name)
                projectActivityIntent.putExtra("description",projectData.description)
                projectActivityIntent.putExtra("startDate",projectData.creationDate)
                projectActivityIntent.putExtra("endDate",projectData.targetDate)
                context.startActivity(projectActivityIntent)
            },
            Response.ErrorListener {  }
        ){
            override fun getHeaders(): Map<String, String> {
                // Create HashMap of your Headers as the example provided below
                val token = sharedPreferences.getString("token","no token")
                val headers = HashMap<String, String>()
                headers["Authorization"] = "Bearer $token"
                return headers
            }
        }

        queue.add(stringRequest)
    }
}