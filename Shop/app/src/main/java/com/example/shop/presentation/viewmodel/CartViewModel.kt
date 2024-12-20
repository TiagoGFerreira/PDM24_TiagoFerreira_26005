package com.example.shop.presentation.viewmodel

import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.example.shop.domain.model.Cart
import com.example.shop.domain.model.Product
import androidx.lifecycle.viewModelScope
import com.example.shop.domain.model.CartItem
import com.example.shop.domain.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val repository: CartRepository) : ViewModel() {
    private val _cart = mutableStateOf(Cart())
    val cart: State<Cart> get() = _cart

    private val _cartTotalItems = mutableStateOf(0)
    val cartTotalItems: State<Int> get() = _cartTotalItems

    private var _cartItems = mutableStateListOf<CartItem>()
    val cartItems: List<CartItem> get() = _cartItems

    init {
        getCartItems()
        getTotalItemsInCart()
    }

    fun addProductToCart(product: Product, quantity: Int) {
        viewModelScope.launch {
            repository.addItemCart(CartItem(product.id, quantity))
            getCartItems()
            getTotalItemsInCart()
        }
    }


    fun removeProductFromCart(productId: Int) {
        viewModelScope.launch {
            val cartItem = _cartItems.find { it.id == productId }
            cartItem?.let {
                repository.removeItemCart(it)
                _cartItems.remove(it)
                getTotalItemsInCart()
            }
        }
    }

    fun clearCart() {
        viewModelScope.launch {
            repository.clearCart(_cart.value)
            _cartItems.clear()
            getTotalItemsInCart()
        }
    }

    fun getCartItems() {
        viewModelScope.launch {
            val items = repository.getCartItems()
            _cartItems.clear()
            _cartItems.addAll(items)
        }
    }

    fun getTotalItemsInCart() {
        viewModelScope.launch {
            val totalItems = repository.getTotalItems()
            _cartTotalItems.value = totalItems
        }
    }

    fun shareCartItems(userEmail: String){
        viewModelScope.launch {
            repository.shareCartIems(userEmail,_cart.value)
            getCartItems()
            getTotalItemsInCart()
        }
    }
}
