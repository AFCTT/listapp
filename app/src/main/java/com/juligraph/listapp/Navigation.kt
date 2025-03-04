package com.juligraph.listapp

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.juligraph.listapp.data.User
import com.juligraph.listapp.ui.screens.HomeScreen
import com.juligraph.listapp.ui.screens.UserDetail
import kotlinx.serialization.Serializable

@Serializable
sealed interface Routes {
    @Serializable
    object Home

    @Serializable
    data class UserDetail(val id: Int)
}

@Composable
fun NavigationStack(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    users: List<User>
) {
    NavHost(navController = navController, startDestination = Routes.Home, modifier = modifier) {
        composable<Routes.Home> {
            HomeScreen(navController = navController)
        }
        composable("userDetail/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: 0
            Log.d("Navigation", "Navigating to UserDetail with id: $id")
            UserDetail(id = id, users = users)
        }
    }
}