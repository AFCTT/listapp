package com.juligraph.listapp.data

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val image: String,
    val phone: String,
    val email: String? = null,
    val age: Int? = null,
    val gender: String? = null,
    val birthDate: String? = null,
    val company: Company? = null
)

@Serializable
data class Company(
    val name: String
)

@Serializable
data class UserResponse(
    val users: List<User> // Cambiamos "results" por "users"
)