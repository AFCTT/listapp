package com.juligraph.listapp.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import java.lang.reflect.Modifier

@Composable
fun StatusBadge(status: String) {
    val color = when (status) {
        "Alive" -> Color.Green
        "Dead" -> Color.Red
        else -> Color.Gray
    }
    Icon(
        imageVector = Icons.Rounded.ThumbUp,
        contentDescription = null,
        tint = color,
    )
}