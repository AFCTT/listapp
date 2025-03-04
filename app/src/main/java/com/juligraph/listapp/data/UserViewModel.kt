package com.juligraph.listapp.data

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juligraph.listapp.network.KtorClient
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    var users by mutableStateOf<List<User>>(emptyList())
    private val apiClient = KtorClient()

    init {
        loadUsers()
    }

    private fun loadUsers() {
        viewModelScope.launch {
            try {
                val response = apiClient.getUsers()
                users = response.users
                Log.d("UserViewModel", "Loaded ${users.size} users")
            } catch (e: Exception) {
                Log.e("UserViewModel", "Error loading users", e)
            }
        }
    }
}