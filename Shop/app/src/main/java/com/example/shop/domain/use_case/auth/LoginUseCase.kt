package com.example.shop.domain.use_case.auth

import com.example.shop.data.model.users.UserDTO
import com.example.shop.data.repository.AuthState
import com.example.shop.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(userDTO: UserDTO): AuthState {
        return authRepository.loginUser(userDTO)
    }
}