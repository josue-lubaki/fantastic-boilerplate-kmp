package com.alithya.boilerplate.features.login.presentation

/**
 * created by josuelubaki
 * date : 2023-12-06
 * version : 1.0.0
 */

sealed class LoginState {
    data object Idle : LoginState()
    data object Loading : LoginState()
    data object Success : LoginState()
    data class Error(val message: String) : LoginState()
}

