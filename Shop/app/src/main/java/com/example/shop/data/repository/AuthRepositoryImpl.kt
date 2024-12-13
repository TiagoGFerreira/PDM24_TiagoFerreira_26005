package com.example.shop.data.repository

import com.example.shop.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor() : AuthRepository {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    init {
        checkAuthStatus()
    }

    private fun checkAuthStatus(): AuthState {
        return if (auth.currentUser == null) {
            AuthState.Unauthenticated
        } else {
            AuthState.Authenticated
        }
    }

    override suspend fun registerUser(email: String, password: String): AuthState {
        if (email.isEmpty() || password.isEmpty()) {
            return AuthState.Error("Email or password cannot be empty")
        }

        return try {
            auth.createUserWithEmailAndPassword(email, password)
                .await() // Espera a resposta da operação
            AuthState.Authenticated
        } catch (e: Exception) {
            AuthState.Error(e.message ?: "Unexpected error occurred")
        }
    }

    override suspend fun loginUser(email: String, password: String): AuthState {
        if (email.isEmpty() || password.isEmpty()) {
            return AuthState.Error("Email or password cannot be empty")
        }

        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            if (result.user != null) {
                AuthState.Authenticated
            } else {
                AuthState.Error("Authentication failed")
            }
        } catch (e: Exception) {
            AuthState.Error(e.message ?: "Unexpected error occurred")
        }
    }

    override suspend fun signout() {
        auth.signOut()
    }
}
