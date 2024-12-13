package com.example.shop.data.repository

sealed class AuthState {
    object Authenticated : AuthState()
    object Unauthenticated : AuthState()
    data class Error(val message : String) : AuthState()
}