package com.example.shop.domain.use_case.cart

import com.example.shop.domain.model.CartItem
import com.example.shop.domain.repository.CartRepository
import javax.inject.Inject

class AddItemToCartUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {
    suspend operator fun invoke(cartItem: CartItem) {
        cartRepository.addItemCart(cartItem)
    }
}

