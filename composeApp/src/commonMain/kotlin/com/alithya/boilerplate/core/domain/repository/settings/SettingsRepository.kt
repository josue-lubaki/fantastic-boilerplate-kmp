package com.alithya.boilerplate.core.domain.repository.settings

import kotlinx.coroutines.flow.Flow

/**
 * created by Josue Lubaki
 * date : 2023-12-04
 * version : 1.0.0
 */

interface SettingsRepository {
    suspend fun saveAppTheme(theme: Int)
    fun getAppTheme() : Flow<Int?>

    suspend fun saveAccessToken(accessToken: String)

    fun getAccessToken() : Flow<String?>
}