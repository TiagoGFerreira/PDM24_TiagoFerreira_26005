package com.example.shop.domain.repository

import com.example.shop.data.repository.AuthState

interface AuthRepository {
    fun checkAuthStatus(): AuthState
    suspend fun registerUser(email : String, password : String): AuthState
    suspend fun loginUser(email: String, password : String): AuthState
    suspend fun signout()
}