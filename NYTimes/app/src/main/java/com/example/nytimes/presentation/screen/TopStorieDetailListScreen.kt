import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.nytimes.presentation.viewModel.TopStorieDetailListViewModel

@Composable
fun TopStorieDetailListScreen(
    topStorieId: Int,
    viewModel: TopStorieDetailListViewModel,
    onBack: () -> Unit
) {
    val storyDetail = viewModel.topStorieDetail.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchTopStorieDetail(topStorieId, "e35283d0696b465bbd3cefc5ee783934")
    }

    storyDetail.value?.let { detail ->
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                AsyncImage(
                    model = detail.image,
                    contentDescription = "Image of the story",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(bottom = 16.dp),
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = detail.title,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {
                    Text(
                        text = "By ${detail.author}",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Gray
                        )
                    )
                    Text(
                        text = detail.publish_date,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color.Gray
                        )
                    )
                }

                Card(
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(4.dp),
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = detail.text,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = MaterialTheme.colorScheme.onSurface
                            ),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        if (detail.summary.isNotBlank()) {
                            Text(
                                text = "Summary:",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                ),
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                            Text(
                                text = detail.summary,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            )
                        }
                    }
                }
                Button(
                    onClick = onBack,
                    shape = RoundedCornerShape(50),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("Back")
                }
            }
        }
    } ?: Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Loading details...",
            style = MaterialTheme.typography.bodyLarge.copy(
                color = Color.Gray
            )
        )
    }
}
