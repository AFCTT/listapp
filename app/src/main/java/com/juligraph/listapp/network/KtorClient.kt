package com.juligraph.listapp.network

import com.juligraph.listapp.data.User
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

class KtorClient {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    @Serializable
    data class UsersResponse(
        val users: List<User>,
        val total: Int,
        val skip: Int,
        val limit: Int
    )

    suspend fun getUsers(): UsersResponse {
        return client.get("https://dummyjson.com/users").body()
    }
}