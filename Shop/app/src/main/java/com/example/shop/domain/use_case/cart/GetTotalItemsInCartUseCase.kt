package com.example.shop.domain.use_case.cart

import com.example.shop.domain.repository.CartRepository
import javax.inject.Inject

class GetTotalItemsInCartUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {
    suspend operator fun invoke(): Int {
        return cartRepository.getTotalItems()
    }
}

