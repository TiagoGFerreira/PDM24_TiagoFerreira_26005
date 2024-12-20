package com.example.shop.domain.model

data class Cart(
    val email: String = "",
    val products: List<CartItem> = listOf()
)