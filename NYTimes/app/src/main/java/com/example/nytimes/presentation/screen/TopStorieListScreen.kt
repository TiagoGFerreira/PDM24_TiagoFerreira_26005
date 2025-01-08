package com.example.nytimes.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.nytimes.domain.model.top_news.NewsItem
import com.example.nytimes.presentation.viewModel.TopStorieListViewModel

@Composable
fun TopStorieListScreen(viewModel: TopStorieListViewModel, source : String, language : String,onItemClick: (Int) -> Unit) {
    val stories = viewModel.topstorie.collectAsState().value

    LaunchedEffect(source,language) {
        viewModel.fetchTopStorie(source,language)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Top Stories",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (stories.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Loading stories...",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Gray
                )
            }
        } else {
            LazyColumn {
                items(stories) { story ->
                    TopStorieItem(story = story, onClick = { onItemClick(story.id) })
                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 8.dp),
                        thickness = 1.dp,
                        color = Color.LightGray
                    )
                }
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
        Text(
            text = story.title,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier.padding(bottom = 4.dp)
        )

        if (!story.author.isNullOrBlank()) {
            Text(
                text = "By ${story.author}",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }


        if (!story.summary.isNullOrBlank()) {
            Text(
                text = story.summary,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}
