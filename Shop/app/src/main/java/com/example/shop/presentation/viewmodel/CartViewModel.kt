package com.example.shop.presentation.viewmodel


import androidx.lifecycle.ViewModel
import com.example.shop.domain.model.Cart
import com.example.shop.domain.model.Product
import androidx.lifecycle.viewModelScope
import com.example.shop.domain.model.CartItem
import com.example.shop.domain.use_case.cart.AddItemToCartUseCase
import com.example.shop.domain.use_case.cart.ClearCartUseCase
import com.example.shop.domain.use_case.cart.GetCartItemsUseCase
import com.example.shop.domain.use_case.cart.GetTotalItemsInCartUseCase
import com.example.shop.domain.use_case.cart.RemoveItemFromCartUseCase
import com.example.shop.domain.use_case.cart.ShareCartItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val addItemToCartUseCase: AddItemToCartUseCase,
    private val removeItemFromCartUseCase: RemoveItemFromCartUseCase,
    private val clearCartUseCase: ClearCartUseCase,
    private val getCartItemsUseCase: GetCartItemsUseCase,
    private val getTotalItemsInCartUseCase: GetTotalItemsInCartUseCase,
    private val shareCartItemsUseCase: ShareCartItemsUseCase
) : ViewModel() {

    private val _cart = MutableStateFlow(Cart())
    val cart: StateFlow<Cart> get() = _cart

    private val _cartTotalItems = MutableStateFlow(0)
    val cartTotalItems: StateFlow<Int> get() = _cartTotalItems

    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> get() = _cartItems

    init {
        getCartItems()
        getTotalItemsInCart()
    }

    fun addProductToCart(product: Product, quantity: Int) {
        viewModelScope.launch {
            addItemToCartUseCase(CartItem(product.id, quantity))
            getCartItems()
            getTotalItemsInCart()
        }
    }

    fun removeProductFromCart(productId: String) {
        viewModelScope.launch {
            val cartItem = _cartItems.value.find { it.id == productId }
            cartItem?.let {
                removeItemFromCartUseCase(it)
                _cartItems.value = _cartItems.value.filter { it.id != productId }
                getTotalItemsInCart()
            }
        }
    }

    fun clearCart() {
        viewModelScope.launch {
            clearCartUseCase(_cart.value)
            _cartItems.value = emptyList()
            getTotalItemsInCart()
        }
    }


    fun getCartItems() {
        viewModelScope.launch {
            val items = getCartItemsUseCase()
            _cartItems.value = items
        }
    }


    fun getTotalItemsInCart() {
        viewModelScope.launch {
            val totalItems = getTotalItemsInCartUseCase()
            _cartTotalItems.value = totalItems
        }
    }

    fun shareCartItems(userEmail: String) {
        viewModelScope.launch {
            shareCartItemsUseCase(userEmail, _cart.value)
            getCartItems()
            getTotalItemsInCart()
        }
    }

    fun resetCartState() {
        _cartItems.value = emptyList()
        _cartTotalItems.value = 0
    }

    fun calculateCartTotal(products: List<Product>): Double {
        return _cartItems.value.sumOf { cartItem ->
            val product = products.find { it.id == cartItem.id }
            product?.price?.times(cartItem.quantity) ?: 0.0
        }
    }




}
