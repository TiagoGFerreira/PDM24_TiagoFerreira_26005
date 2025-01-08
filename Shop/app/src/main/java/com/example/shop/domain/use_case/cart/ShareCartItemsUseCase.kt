package com.example.shop.domain.use_case.cart

import com.example.shop.domain.repository.CartRepository
import javax.inject.Inject

class ShareCartItemsUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {
    suspend operator fun invoke(emailOtherUser: String): String {
        return cartRepository.shareCartItems(emailOtherUser)
    }
}
