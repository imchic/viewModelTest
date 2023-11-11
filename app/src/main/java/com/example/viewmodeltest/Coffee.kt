package com.example.viewmodeltest

data class Coffee(
    val id: Int,
    val title: String,
    val description: String,
    val ingredients: List<String>,
    val price: Double,
    val image: String
)
