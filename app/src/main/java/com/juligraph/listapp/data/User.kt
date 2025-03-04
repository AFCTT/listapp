package com.juligraph.listapp.data

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phone: String,
    val gender: String,
    val image: String

)

@Serializable
data class Company(
    val name: String
)