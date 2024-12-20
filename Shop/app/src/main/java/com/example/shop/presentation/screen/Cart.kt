package com.example.shop.presentation.screen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shop.domain.model.CartItem
import com.example.shop.presentation.viewmodel.CartViewModel
import com.example.shop.presentation.viewmodel.ProductViewModel

@Composable
fun CartScreen(
    productViewModel: ProductViewModel,
    cartViewModel: CartViewModel,
) {
    val products by productViewModel.products.collectAsState()
    val cartItems = cartViewModel.cartItems
    val userEmail = remember { mutableStateOf("") }
    var reloadTrigger by remember { mutableStateOf(false) }

    LaunchedEffect(reloadTrigger) { // Executa sempre que reloadTrigger muda
        cartViewModel.getCartItems()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text("Carrinho", style = MaterialTheme.typography.headlineMedium)

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(cartItems) { cartItem ->
                val product = products.find { it.id == cartItem.id }
                product?.let {
                    CartItemRow(
                        productName = it.name,
                        quantity = cartItem.quantity,
                        onRemove = { cartViewModel.removeProductFromCart(cartItem.id) }
                    )
                }
            }
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


        Row(modifier = Modifier.padding(16.dp)) {
            Button(onClick = { cartViewModel.clearCart() }, modifier = Modifier.weight(1f)) {
                Text("Limpar Carrinho")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { /* Avançar para o pagamento */ }, modifier = Modifier.weight(1f)) {
                Text("Avançar")
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
            Icon(Icons.Default.Delete, contentDescription = "Remover")
        }
    }
}
