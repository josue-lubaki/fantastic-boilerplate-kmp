package com.alithya.boilerplate.features.login.presentation

/**
 * created by josuelubaki
 * date : 2023-12-06
 * version : 1.0.0
 */

sealed class LoginEvent {
    data class OnLogin(val email: String, val password : String) : LoginEvent()
}

