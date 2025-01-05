package com.example.shop.domain.use_case.cart

import com.example.shop.domain.model.Cart
import com.example.shop.domain.repository.CartRepository
import javax.inject.Inject

class ClearCartUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {
    suspend operator fun invoke(cart: Cart) {
        cartRepository.clearCart(cart)
    }
}