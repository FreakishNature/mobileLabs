package com.example.mobiledev2.model

data class ProjectData(
    val name: String,
    val description: String,
    val owner: String,
    val investedSum : Double,
    val targetSum : Double,
    val category: String,
    val creationDate: String,
    val targetDate: String
)