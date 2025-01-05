package com.example.shop.data.repository

import com.example.shop.data.model.users.UserDTO
import com.example.shop.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor() : AuthRepository {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun checkAuthStatus(): AuthState {
        return if (auth.currentUser == null) {
            AuthState.Unauthenticated
        } else {
            AuthState.Authenticated
        }
    }

    override suspend fun registerUser(userDTO: UserDTO): AuthState {
        return try {
            auth.createUserWithEmailAndPassword(userDTO.email, userDTO.password).await()
            AuthState.Authenticated
        } catch (e: Exception) {
            AuthState.Error(e.message ?: "Unexpected error occurred")
        }
    }

    override suspend fun loginUser(userDTO: UserDTO): AuthState {
        return try {
            val result = auth.signInWithEmailAndPassword(userDTO.email, userDTO.password).await()
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
