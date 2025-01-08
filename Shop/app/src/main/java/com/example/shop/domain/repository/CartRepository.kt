package com.example.shop.domain.repository

import com.example.shop.domain.model.Cart
import com.example.shop.domain.model.CartItem

interface CartRepository{
    suspend fun addItemCart(cartItem: CartItem)
    suspend fun removeItemCart(cartItem: CartItem)
    suspend fun clearCart(cart: Cart)
    suspend fun getTotalItems(): Int
    suspend fun getCartItems(): List<CartItem>
    suspend fun shareCartItems(emailOtherUser: String): String
}