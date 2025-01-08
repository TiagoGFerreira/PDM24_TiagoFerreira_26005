package com.example.shop.presentation.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.shop.presentation.viewmodel.CartViewModel
import com.example.shop.presentation.viewmodel.ProductViewModel
import kotlinx.coroutines.delay

@SuppressLint("DefaultLocale")
@Composable
fun CartScreen(
    productViewModel: ProductViewModel,
    cartViewModel: CartViewModel,
    onNavigateToPay: () -> Unit
) {
    val message by cartViewModel.shareCartMessage.collectAsState()
    val products by productViewModel.products.collectAsState()
    val cartItems by cartViewModel.cartItems.collectAsState()
    val userEmail = remember { mutableStateOf("") }
    val totalPrice = remember(products, cartItems) {
        cartViewModel.calculateCartTotal(products)
    }
    var reloadTrigger by remember { mutableStateOf(false) }
    var showMessage by remember { mutableStateOf(false) }

    LaunchedEffect(reloadTrigger) {
        cartViewModel.getCartItems()
        cartViewModel.getTotalItemsInCart()
        cartViewModel.calculateCartTotal(products)
    }

    LaunchedEffect(message) {
        if (!message.isNullOrEmpty()) {
            showMessage = true
            delay(3000)
            showMessage = false
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text("Cart", style = MaterialTheme.typography.headlineMedium)

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(cartItems) { cartItem ->
                val product = products.find { it.id == cartItem.id }
                product?.let {
                    CartItemRow(
                        productName = it.name,
                        quantity = cartItem.quantity,
                        onRemove = {
                            cartViewModel.removeProductFromCart(cartItem.id)
                        }
                    )
                }
            }
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Total:", style = MaterialTheme.typography.bodyMedium)
            Text(
                text = "${String.format("%.2f", totalPrice)} €",
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Use cart from your friend:",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        OutlinedTextField(
            value = userEmail.value,
            onValueChange = { userEmail.value = it },
            label = { Text("Friend's Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Button(
            onClick = {
                if (userEmail.value.isNotEmpty()) {
                    cartViewModel.shareCartItems(userEmail.value)
                    reloadTrigger = !reloadTrigger
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Add friend's cart")
        }

        if (showMessage) {
            message?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        Row(modifier = Modifier.padding(16.dp)) {
            Button(onClick = { cartViewModel.clearCart() }, modifier = Modifier.weight(1f)) {
                Text("Clear Cart")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { onNavigateToPay() }, modifier = Modifier.weight(1f)) {
                Text("Advance")
            }
        }
    }
}

@Composable
fun CartItemRow(
    productName: String,
    quantity: Int,
    onRemove: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = productName,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyMedium
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "$quantity", style = MaterialTheme.typography.bodyLarge)
        }

        IconButton(onClick = onRemove) {
            Icon(Icons.Default.Delete, contentDescription = "Remove")
        }
    }
}

