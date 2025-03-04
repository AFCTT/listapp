package com.juligraph.listapp.ui.screens

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import androidx.compose.foundation.rememberScrollState
import com.juligraph.listapp.data.User
import com.juligraph.listapp.ui.theme.AppTheme
import com.juligraph.listapp.ui.components.Loader

@Composable
fun UserDetail(id: Int, users: List<User>) {
    val context = LocalContext.current
    val user = remember(users, id) { users.find { it.id == id } }
    Log.d("UserDetail", "User for id $id: ${user?.firstName ?: "null"}")

    if (user == null) {
        Loader(modifier = Modifier.fillMaxWidth())
    } else {
        // Bono: Gradiente animado detrás de la imagen
        val infiniteTransition = rememberInfiniteTransition()
        val alpha by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 2000),
                repeatMode = androidx.compose.animation.core.RepeatMode.Reverse
            )
        )
        val gradient = Brush.verticalGradient(
            colors = listOf(Color.Blue.copy(alpha), Color.Green.copy(alpha)),
            startY = 0f,
            endY = 300f
        )

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(gradient)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Bono: Imagen redonda con sombra y borde animado
                    AsyncImage(
                        model = user.image,
                        contentDescription = "User picture",
                        modifier = Modifier
                            .aspectRatio(1f)
                            .fillMaxWidth(0.5f)
                            .clip(CircleShape)
                            .shadow(8.dp, CircleShape)
                            .border(
                                width = 2.dp,
                                brush = Brush.sweepGradient(listOf(Color.Red, Color.Blue)),
                                shape = CircleShape
                            )
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    // Bono: Nombre y apellido con gradiente animado
                    val nameAlpha by infiniteTransition.animateFloat(
                        initialValue = 0f,
                        targetValue = 1f,
                        animationSpec = infiniteRepeatable(
                            animation = tween(durationMillis = 2000),
                            repeatMode = androidx.compose.animation.core.RepeatMode.Reverse
                        )
                    )
                    val nameGradient = Brush.horizontalGradient(
                        colors = listOf(Color.Magenta.copy(nameAlpha), Color.Yellow.copy(nameAlpha))
                    )
                    Row {
                        Text(
                            text = "${user.firstName} ${user.lastName}",
                            modifier = Modifier
                                .background(nameGradient)
                                .padding(4.dp),
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.Black,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    mapOf(
                        "Gender" to user.gender,
                        "Location" to "Not available",
                        "Origin" to "Earth (C-137)",
                        "Species" to "Human",
                        "Status" to "Active",
                        "Email" to user.email,
                        "Phone" to user.phone,
                        "Image URL" to user.image
                    ).forEach { entry ->
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 12.dp)
                        ) {
                            Text(
                                text = entry.key,
                                modifier = Modifier,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.secondary
                            )
                            Text(
                                text = entry.value ?: "N/A",
                                modifier = Modifier,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.tertiary
                            )
                            if (entry.key == "Phone") {
                                Text(
                                    text = "Call",
                                    modifier = Modifier
                                        .clickable {
                                            val intent = Intent(Intent.ACTION_DIAL).apply {
                                                data = Uri.parse("tel:${user.phone}")
                                            }
                                            context.startActivity(intent)
                                        }
                                        .padding(start = 8.dp),
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserDetailPreview() {
    AppTheme {
        UserDetail(id = 1, users = listOf(User(
            id = 1,
            firstName = "Albert",
            lastName = "Einstein",
            email = "albert@example.com",
            phone = "123-456-7890",
            gender = "Male",
            image = "https://i.pravatar.cc/300" // URL de imagen de prueba válida
        )))
    }
}