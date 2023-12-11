package com.alithya.boilerplate.features.login.domain.models

/**
 * created by Josue Lubaki
 * date : 2023-12-06
 * version : 1.0.0
 */

sealed class LoginStatus {
    data object Success : LoginStatus()
    data class Error(val exception: String) : LoginStatus()
}