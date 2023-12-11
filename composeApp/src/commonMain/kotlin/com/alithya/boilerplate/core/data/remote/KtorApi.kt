package com.alithya.boilerplate.core.data.remote

import com.alithya.boilerplate.BuildKonfig
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.http.path
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

/**
 * created by Josue Lubaki
 * date : 2023-12-06
 * version : 1.0.0
 */

private val BASE_URL = BuildKonfig.BASE_URL

internal abstract class KtorApi {
    protected val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
    }

    fun HttpRequestBuilder.pathUrl(path: String) {
        url {
            takeFrom(BASE_URL)
            path(path)
        }
    }
}