package com.alithya.boilerplate.features.login.data.repository.datasource

import com.alithya.boilerplate.features.login.domain.models.LoginForm
import com.alithya.boilerplate.features.login.domain.models.LoginStatus

/**
 * created by Josue Lubaki
 * date : 2023-12-06
 * version : 1.0.0
 */

interface LoginRemoteDataSource {
    suspend fun login(loginForm: LoginForm) : LoginStatus
}