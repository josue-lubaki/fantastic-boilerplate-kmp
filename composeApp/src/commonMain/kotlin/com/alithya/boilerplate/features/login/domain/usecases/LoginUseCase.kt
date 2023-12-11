package com.alithya.boilerplate.features.login.domain.usecases

import com.alithya.boilerplate.features.login.domain.models.LoginForm
import com.alithya.boilerplate.features.login.domain.models.LoginStatus
import com.alithya.boilerplate.features.login.domain.repository.LoginRepository

/**
 * created by Josue Lubaki
 * date : 2023-12-06
 * version : 1.0.0
 */

class LoginUseCase(
    private val repository: LoginRepository
) {
    suspend operator fun invoke(loginForm : LoginForm) : LoginStatus = repository.login(loginForm = loginForm)
}