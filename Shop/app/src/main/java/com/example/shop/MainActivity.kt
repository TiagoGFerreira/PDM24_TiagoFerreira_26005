package com.example.shop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.shop.presentation.navigation.AuthNavigation
import com.example.shop.presentation.viewmodel.AuthViewModel
import com.example.shop.presentation.viewmodel.ProductViewModel
import com.example.shop.presentation.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val authViewModel: AuthViewModel by viewModels()
    private val productViewModel: ProductViewModel by viewModels()
    private val cartViewModel: CartViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Main(authViewModel = authViewModel, productViewModel = productViewModel, cartViewModel = cartViewModel)
        }
    }
}

@Composable
fun Main(authViewModel: AuthViewModel, productViewModel: ProductViewModel, cartViewModel: CartViewModel) {
    val navController = rememberNavController()

    AuthNavigation(
        navController = navController,
        authViewModel = authViewModel,
        productViewModel = productViewModel,
        cartViewModel = cartViewModel
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
}
