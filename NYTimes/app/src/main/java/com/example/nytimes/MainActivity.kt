package com.example.nytimes

import TopStorieDetailListScreen
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.nytimes.presentation.screen.TopStorieListScreen
import com.example.nytimes.presentation.viewModel.TopStorieListViewModel
import com.example.nytimes.ui.theme.NYTimesTheme
import androidx.lifecycle.viewmodel.compose.viewModel
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
        TopStorieListScreen(topStorieListViewModel,"us","en") { topStorieId ->
            selectedTopStorieId = topStorieId
        }
    } else {
        val topStorieDetailListViewModel: TopStorieDetailListViewModel = viewModel()

        TopStorieDetailListScreen(selectedTopStorieId!!,topStorieDetailListViewModel) {
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