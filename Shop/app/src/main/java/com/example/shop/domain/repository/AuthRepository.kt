package com.example.shop.domain.repository

import com.example.shop.data.model.users.UserDTO
import com.example.shop.data.repository.AuthState

interface AuthRepository {
    fun checkAuthStatus(): AuthState
    suspend fun registerUser(userDTO: UserDTO): AuthState
    suspend fun loginUser(userDTO: UserDTO): AuthState
    suspend fun signout()
}