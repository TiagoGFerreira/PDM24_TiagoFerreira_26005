package com.example.nytimes.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nytimes.domain.model.NewsItem
import com.example.nytimes.presentation.viewModel.TopStorieListViewModel

@Composable
fun TopStorieListScreen(viewModel: TopStorieListViewModel, onItemClick: (Int) -> Unit) {
    val stories = viewModel.topstorie.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.fetchTopStorie()
    }

    if (stories.isEmpty()) {
        Text("Loading stories...")
    } else {
        LazyColumn {
            items(stories) { story ->
                TopStorieItem(story = story, onClick = { onItemClick(story.id) })
            }
        }
    }
}




@Composable
fun TopStorieItem(story: NewsItem, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(8.dp)
    ) {
        Text(text = story.title)
        if (!story.summary.isNullOrBlank()) {
            Text(text = story.summary, modifier = Modifier.padding(top = 4.dp))
        }
    }
}
