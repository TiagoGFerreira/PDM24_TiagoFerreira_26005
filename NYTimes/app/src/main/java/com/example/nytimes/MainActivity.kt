package com.example.nytimes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.compose.ui.tooling.preview.Preview
import com.example.nytimes.presentation.screen.TopStorieDetailScreen
import com.example.nytimes.presentation.screen.TopStorieListScreen
import com.example.nytimes.presentation.viewModel.TopStorieListViewModel
import com.example.nytimes.ui.theme.NYTimesTheme
import com.example.nytimes.presentation.viewModel.TopStorieDetailListViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           MainScreen()
        }
    }
}


@Composable
fun MainScreen() {
    var selectedTopStorieId by remember { mutableStateOf<Int?>(null) }

    if (selectedTopStorieId == null) {
        val topStorieListViewModel: TopStorieListViewModel = viewModel()
        TopStorieListScreen(topStorieListViewModel) { topStorieId ->
            selectedTopStorieId = topStorieId
        }
    } else {
        val topStorieDetailListViewModel: TopStorieDetailListViewModel = viewModel()
        TopStorieDetailScreen(topStorieDetailListViewModel) {
            selectedTopStorieId = null
        }
    }
}





@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NYTimesTheme {
    }
}