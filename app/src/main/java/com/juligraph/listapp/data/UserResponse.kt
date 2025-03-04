package com.juligraph.listapp.data

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val users: List<User>
)


@Serializable
data class Info(
    val count: Int,
    val pages: Int,
)