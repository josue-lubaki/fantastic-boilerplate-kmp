package com.alithya.boilerplate.features.login.data.remote

import com.alithya.boilerplate.core.data.remote.KtorApi
import com.alithya.boilerplate.core.utils.Endpoints
import com.alithya.boilerplate.features.login.data.models.LoginResponse
import io.ktor.client.call.body
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.post
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.statement.request
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.Parameters
import io.ktor.http.contentType
import io.ktor.http.headers
import io.ktor.util.InternalAPI

/**
 * created by Josue Lubaki
 * date : 2023-12-06
 * version : 1.0.0
 */

internal class LoginService : KtorApi() {

    suspend fun login(
        grantType: String,
        clientId: String,
        clientSecret: String,
        username: String,
        password: String
    )  =
        client.post {
            pathUrl(Endpoints.OAUTH_TOKEN) // Base URL + Endpoints.OAUTH_TOKEN
            setBody(FormDataContent(
                Parameters.build {
                    append("grant_type", grantType)
                    append("client_id", clientId)
                    append("client_secret", clientSecret)
                    append("username", username)
                    append("password", password)
                }
            ))
        }
}