package com.example.shop.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shop.data.model.users.UserDTO
import com.example.shop.data.repository.AuthState
import com.example.shop.domain.use_case.auth.CheckAuthStatusUseCase
import com.example.shop.domain.use_case.auth.LoginUseCase
import com.example.shop.domain.use_case.auth.LogoutUseCase
import com.example.shop.domain.use_case.auth.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase,
    private val logoutUseCase: LogoutUseCase,
    checkAuthStatusUseCase: CheckAuthStatusUseCase
) : ViewModel() {

    private val _authState = MutableStateFlow(checkAuthStatusUseCase())
    val authState: StateFlow<AuthState> = _authState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val userDTO = UserDTO(email, password)
            _authState.value = loginUseCase(userDTO)
        }
    }

    fun register(email: String, password: String) {
        viewModelScope.launch {
            val userDTO = UserDTO(email, password)
            _authState.value = registerUseCase(userDTO)
        }
    }

    fun logout() {
        viewModelScope.launch {
            logoutUseCase()
            _authState.value = AuthState.Unauthenticated
        }
    }
}