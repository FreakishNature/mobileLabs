package com.example.mobiledev2

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProjectActivity: AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.project_info)
        findViewById<TextView>(R.id.title).text = intent.getStringExtra("title")
        findViewById<TextView>(R.id.description).text = intent.getStringExtra("description")
        findViewById<TextView>(R.id.startDate).text = intent.getStringExtra("startDate")
        findViewById<TextView>(R.id.endDate).text = intent.getStringExtra("endDate")
    }
}