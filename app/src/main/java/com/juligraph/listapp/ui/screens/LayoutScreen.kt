package com.juligraph.listapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.juligraph.listapp.ui.theme.AppTheme

@Composable
fun LayoutScreen() {
    Row(modifier = Modifier.fillMaxWidth().height(250.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        ) {
        for (i in 1..5) {
            Text("Element $i", modifier = Modifier.height((i*10).dp))
        }
    }
}

@Composable
@Preview
fun LayoutScreenPreview() {
    AppTheme {
        LayoutScreen()
    }
}