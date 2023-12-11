package com.alithya.boilerplate.features.login.data.repository.datasourceimpl

import com.alithya.boilerplate.BuildKonfig
import com.alithya.boilerplate.core.domain.repository.settings.SettingsRepository
import com.alithya.boilerplate.features.login.data.models.LoginResponse
import com.alithya.boilerplate.features.login.data.repository.datasource.LoginRemoteDataSource
import com.alithya.boilerplate.features.login.data.remote.LoginService
import com.alithya.boilerplate.features.login.domain.models.LoginForm
import com.alithya.boilerplate.features.login.domain.models.LoginStatus
import io.ktor.client.call.body
import io.ktor.http.isSuccess

/**
 * created by Josue Lubaki
 * date : 2023-12-06
 * version : 1.0.0
 */

internal class LoginRemoteDataSourceImpl(
    private val service: LoginService,
    private val settingsRepository: SettingsRepository
) : LoginRemoteDataSource {

    companion object {
        private val GRANT_TYPE = BuildKonfig.GRANT_TYPE
        private val CLIENT_ID = BuildKonfig.CLIENT_ID
        private val CLIENT_SECRET = BuildKonfig.CLIENT_SECRET
    }

    override suspend fun login(loginForm: LoginForm): LoginStatus {
        return try {
            val response = service.login(
                grantType = GRANT_TYPE,
                clientId = CLIENT_ID,
                clientSecret = CLIENT_SECRET,
                username = loginForm.email,
                password = loginForm.password
            )
            if (response.status.isSuccess()) {
                // saving the access token
                val result = response.body<LoginResponse>()
                settingsRepository.saveAccessToken(result.access_token.orEmpty())
                LoginStatus.Success
            } else {
                LoginStatus.Error(response.status.description)
            }
        } catch (e: Exception) {
            LoginStatus.Error(e.message.orEmpty())
        }
    }
}