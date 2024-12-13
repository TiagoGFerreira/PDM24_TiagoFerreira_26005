package com.example.shop.domain.repository

interface AuthRepository {
    suspend fun registerUser(email : String, password : String)
    suspend fun 
}