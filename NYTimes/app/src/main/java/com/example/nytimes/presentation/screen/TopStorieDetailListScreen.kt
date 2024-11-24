package com.example.nytimes.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nytimes.presentation.viewModel.TopStorieDetailListViewModel

@Composable
fun TopStorieDetailScreen(
    viewModel: TopStorieDetailListViewModel,
    onBack: () -> Unit
) {
    val storyDetail = viewModel.topStorieDetail.collectAsState()

    storyDetail.value?.let { detail ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(text = detail.title, modifier = Modifier.padding(bottom = 8.dp))
            Text(text = detail.text, modifier = Modifier.padding(bottom = 8.dp))
            Text(text = detail.summary, modifier = Modifier.padding(bottom = 16.dp))
            Button(onClick = onBack) {
                Text("Back")
            }
        }
    } ?: Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text("Loading...")
    }
}
