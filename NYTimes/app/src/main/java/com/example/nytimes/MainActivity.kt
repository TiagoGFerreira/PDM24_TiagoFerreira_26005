package com.example.nytimes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nytimes.presentation.coin_list.TopStorieListViewModel
import com.example.nytimes.ui.theme.NYTimesTheme
import com.example.nytimes.presentation.coin_list
import com.example.nytimes.presentation.coin_list.TopStorieDetailListViewModel

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

    val topStorieListViewModel: TopStorieListViewModel = viewModel()

    val topStorieDetailListViewModel: TopStorieDetailListViewModel = viewModel()


    if (selectedTopStorieId == null) {

        TopStorieListScreen(topStorieListViewModel) { topStorieId ->
            selectedTopStorieId = topStorieId
        }
    } else {
        topStorieDetailListViewModel.fetchTopStorieDetail(selectedTopStorieId.toString(), "en", "us")
        TopStorieDetailScreen(topStorieDetailListViewModel) {
            selectedTopStorieId = null
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NYTimesTheme {
        Greeting("Android")
    }
}