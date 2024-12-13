package com.example.shop.domain.repository

import com.example.shop.data.repository.AuthState

interface AuthRepository {
    suspend fun registerUser(email : String, password : String): AuthState
    suspend fun loginUser(email: String, password : String): AuthState
    suspend fun signout()
}