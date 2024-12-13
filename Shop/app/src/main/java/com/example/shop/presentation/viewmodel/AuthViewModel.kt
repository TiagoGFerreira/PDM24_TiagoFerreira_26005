package com.example.shop.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shop.domain.repository.AuthRepository
import com.example.shop.data.repository.AuthRepositoryImpl.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _authState  = MutableStateFlow<AuthState>(AuthState.Unauthenticated)
    val authState: StateFlow<AuthState> = _authState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                repository.loginUser(email, password)
                _authState.emit(AuthState.Authenticated)
            } catch (e: Exception) {
                _authState.emit(AuthState.Error(e.message ?: "Unknown error"))
            }
        }
    }

    fun register(email: String, password: String) {
        viewModelScope.launch {
            try {
                repository.registerUser(email, password)
                _authState.emit(AuthState.Authenticated)
            } catch (e: Exception) {
                _authState.emit(AuthState.Error(e.message ?: "Unknown error"))
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            repository.signout()
            _authState.emit(AuthState.Unauthenticated)
        }
    }
}