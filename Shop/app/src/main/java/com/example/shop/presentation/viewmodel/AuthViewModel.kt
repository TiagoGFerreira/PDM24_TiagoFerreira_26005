package com.example.shop.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shop.domain.repository.AuthRepository
import com.example.shop.data.repository.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Unauthenticated)
    val authState: StateFlow<AuthState> = _authState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val state = repository.loginUser(email, password)
                _authState.value = state
            } catch (e: Exception) {
                _authState.value = AuthState.Error("Erro ao realizar login: ${e.message}")
            }
        }
    }

    fun register(email: String, password: String) {
        viewModelScope.launch {
            try {
                val state = repository.registerUser(email, password)
                _authState.value = state
            } catch (e: Exception) {
                _authState.value = AuthState.Error("Erro ao realizar registro: ${e.message}")
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            repository.signout()
            _authState.value = AuthState.Unauthenticated
        }
    }
}
