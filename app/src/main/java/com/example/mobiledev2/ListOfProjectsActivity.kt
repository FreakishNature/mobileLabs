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
import com.example.mobiledev2.presenters.ListOfProjectsPresenter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

class ListOfProjectsActivity: AppCompatActivity()  {
    var listOfProjectsPresenter = ListOfProjectsPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_projects)

        var recyclerView: RecyclerView = findViewById(R.id.projectList)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)

        listOfProjectsPresenter.loadProjects(recyclerView)
    }


}