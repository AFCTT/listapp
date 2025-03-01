package com.juligraph.listapp.data

import kotlinx.serialization.Serializable

@Serializable
data class UsersList(
    val info: Info,
    val results: List<User>
)

@Serializable
data class Info(
    val count: Int,
    val pages: Int,
)