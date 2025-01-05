package com.example.shop.domain.use_case.auth

import com.example.shop.data.repository.AuthState
import com.example.shop.domain.repository.AuthRepository
import javax.inject.Inject

class CheckAuthStatusUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(): AuthState {
        return authRepository.checkAuthStatus()
    }
}

