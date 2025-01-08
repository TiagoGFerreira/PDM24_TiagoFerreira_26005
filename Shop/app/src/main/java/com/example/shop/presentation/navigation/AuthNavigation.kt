package com.example.shop.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.shop.presentation.screen.LoginScreen
import com.example.shop.presentation.screen.RegisterScreen
import com.example.shop.presentation.viewmodel.AuthViewModel
import com.example.shop.data.repository.AuthState
import com.example.shop.presentation.screen.CartScreen
import com.example.shop.presentation.screen.HomeScreen
import com.example.shop.presentation.screen.PayScreen
import com.example.shop.presentation.viewmodel.ProductViewModel
import com.example.shop.presentation.viewmodel.CartViewModel

@Composable
fun AuthNavigation(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    productViewModel: ProductViewModel,
    cartViewModel: CartViewModel
) {
    val authState by authViewModel.authState.collectAsState()

    NavHost(navController = navController, startDestination = when (authState) {
        is AuthState.Authenticated -> "home"
        else -> "login"
    }) {

        composable("login") {
            LoginScreen(
                onLogin = { email, password -> authViewModel.login(email, password) },
                onNavigateToRegister = { navController.navigate("register") },
                authState = authState
            )
        }

        composable("register") {
            RegisterScreen(
                onRegister = { email, password -> authViewModel.register(email, password) },
                onNavigateToLogin = { navController.navigate("login") },
                authState = authState
            )
        }

        composable("home") {
            HomeScreen(
                authViewModel = authViewModel,
                productViewModel = productViewModel,
                cartViewModel = cartViewModel,
                onNavigateToCart = { navController.navigate("cart")},
                onNavigateToLogin = { navController.navigate("login") }
            )
        }
        composable("cart"){
            CartScreen(
                productViewModel = productViewModel,
                cartViewModel = cartViewModel,
                onNavigateToPay = { navController.navigate("pay")}
            )
        }
        composable("pay"){
            PayScreen(cartViewModel = cartViewModel,
                onNavigateToHome = { navController.navigate("home")}
            )
        }
    }
}
