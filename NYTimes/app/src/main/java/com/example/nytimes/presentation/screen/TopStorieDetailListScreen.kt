package com.example.nytimes.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nytimes.presentation.viewModel.TopStorieDetailListViewModel

@Composable
fun TopStorieDetailScreen(
    viewModel: TopStorieDetailListViewModel,
    onBack: () -> Unit
) {
    val topStorieDetail = viewModel.topStorieDetail.collectAsState().value


    LaunchedEffect(Unit) {
        viewModel.fetchTopStorieDetail(
            topStorieId = "1",
            language = "en",
            srccountry = "US"
        )
    }

    Column {
        Button(onClick = onBack) {
            Text("Back")
        }

        topStorieDetail?.let { detail ->
            Text(
                text = detail.title,
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = detail.text,
                modifier = Modifier.padding(16.dp)
            )
        } ?: Text(
            text = "Loading...",
            modifier = Modifier.padding(16.dp)
        )
    }
}
