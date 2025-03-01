package com.juligraph.listapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.juligraph.listapp.data.User
import com.juligraph.listapp.network.KtorClient
import com.juligraph.listapp.ui.components.Loader
import com.juligraph.listapp.ui.theme.AppTheme
import com.juligraph.listapp.R
import com.juligraph.listapp.ui.components.StatusBadge

@Composable
fun UserDetail(id: Int, apiClient: KtorClient = KtorClient()) {
    val context = LocalContext.current
    var user by remember { mutableStateOf<User?>(null) }

    LaunchedEffect(key1 = null) {
        user = apiClient.getUser(id)
    }

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        if (user == null) {
            item {
                Loader(modifier = Modifier.fillParentMaxSize())
            }
        } else {
            item {
                Card(
                    modifier = Modifier
                        .fillParentMaxSize()
                        .padding(8.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                            .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AsyncImage(
                            model = user!!.image,
                            contentDescription = "User picture",
                            modifier = Modifier
                                .aspectRatio(1f)
                                .fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Row {
                            Text(
                                user!!.firstName,
                                color = MaterialTheme.colorScheme.primary,
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.ExtraBold,
                            )
                           // StatusBadge(status = user!!.status)
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        mapOf(
                            stringResource(R.string.gender_label) to user!!.gender,
                            stringResource(R.string.location_label) to user!!.firstName,
                            stringResource(R.string.origin_label) to user!!.phone,
                            stringResource(R.string.species_label) to user!!.birthDate,
                           // stringResource(R.string.status_label) to user!!.status
                        ).forEach { entry ->
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 12.dp)
                            ) {
                                Text(
                                    entry.key,
                                    modifier = Modifier,
                                    color = MaterialTheme.colorScheme.secondary,
                                    style = MaterialTheme.typography.bodyMedium,
                                )
                                entry.value?.let {
                                    Text(
                                        it,
                                        modifier = Modifier,
                                        color = MaterialTheme.colorScheme.tertiary,
                                        style = MaterialTheme.typography.bodyMedium,
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
}

@Preview(showBackground = true)
@Composable
fun UserDetailPreview() {
    AppTheme {
        UserDetail(id = 1)
    }
}