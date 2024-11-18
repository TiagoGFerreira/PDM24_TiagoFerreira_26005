package com.example.nytimes.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nytimes.presentation.viewModel.TopStorieListViewModel

@Composable
fun TopStorieListScreen(
    viewModel: TopStorieListViewModel,
    onTopStorieSelected: (Int) -> Unit
) {
    val topStories = viewModel.topstorie.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.fetchTopStorie()
    }

    LazyColumn {
        items(topStories) { story ->
            Text(
                text = story.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onTopStorieSelected(story.id) }
                    .padding(16.dp)
            )
        }
    }
}
