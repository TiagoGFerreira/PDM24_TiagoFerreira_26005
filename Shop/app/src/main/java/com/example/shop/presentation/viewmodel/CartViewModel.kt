package com.example.shop.presentation.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.shop.domain.model.Cart
import com.example.shop.domain.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor() : ViewModel() {
    private val _cart = mutableStateOf(Cart())
    val cartItemCount: State<Int> = derivedStateOf { _cart.value.items.size }
    val totalPrice: State<Double> = derivedStateOf { _cart.value.getTotalPrice() }


    fun addProductToCart(product: Product) {
        _cart.value.addProduct(product)
    }

    fun removeProductFromCart(productId: Int) {
        _cart.value.removeProduct(productId)
    }

    fun clearCart() {
        _cart.value.clear()
    }
}