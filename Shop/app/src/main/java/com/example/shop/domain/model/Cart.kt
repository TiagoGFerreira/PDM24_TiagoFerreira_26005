package com.example.shop.domain.model

data class CartItem(
    val product: Product,
    var quantity: Int
)


class Cart {
    val items = mutableListOf<CartItem>()

    fun addProduct(product: Product) {
        val existingProduct = items.find { it.product.id == product.id }
        if (existingProduct != null) {
            existingProduct.quantity += 1
        } else {
            items.add(CartItem(product, 1))
        }
    }

    fun removeProduct(productId: Int) {
        items.removeAll { it.product.id == productId }
    }

    fun getTotalPrice(): Double {
        return items.sumOf { it.product.price * it.quantity }
    }

    fun clear() {
        items.clear()
    }
}