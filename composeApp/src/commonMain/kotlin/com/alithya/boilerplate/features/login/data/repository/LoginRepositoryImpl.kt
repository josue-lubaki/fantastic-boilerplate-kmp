package com.alithya.boilerplate.features.login.data.repository

import com.alithya.boilerplate.features.login.data.repository.datasource.LoginRemoteDataSource
import com.alithya.boilerplate.features.login.domain.models.LoginForm
import com.alithya.boilerplate.features.login.domain.models.LoginStatus
import com.alithya.boilerplate.features.login.domain.repository.LoginRepository

/**
 * created by Josue Lubaki
 * date : 2023-12-06
 * version : 1.0.0
 */

class LoginRepositoryImpl(
    private val remoteDataSource: LoginRemoteDataSource
) : LoginRepository {

    override suspend fun login(loginForm: LoginForm): LoginStatus {
        return remoteDataSource.login(loginForm = loginForm)
    }
}