package com.example.shop.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shop.presentation.viewmodel.CartViewModel

@Composable
fun PayScreen(
    cartViewModel: CartViewModel,
    onNavigateToHome: () -> Unit
) {
    var selectedOption by remember { mutableStateOf("Credit Card") }
    val paymentOptions = listOf("Credit Card", "PayPal", "Google Pay")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Select Payment Method",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(16.dp))

        paymentOptions.forEach { option ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            ) {
                RadioButton(
                    selected = (option == selectedOption),
                    onClick = { selectedOption = option }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = option, style = MaterialTheme.typography.bodyLarge)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))


        Button(
            onClick = { cartViewModel.clearCart()
                onNavigateToHome()  },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Confirm Payment")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PayScreenPreview() {
}
