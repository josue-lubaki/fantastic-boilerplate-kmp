package com.alithya.boilerplate.core.data.repository.settings

import com.alithya.boilerplate.core.data.local.settings.PreferenceManager
import com.alithya.boilerplate.core.domain.repository.settings.SettingsRepository
import kotlinx.coroutines.flow.Flow

/**
 * created by Josue Lubaki
 * date : 2023-12-04
 * version : 1.0.0
 */

class SettingsRepositoryImpl(
    private val preferenceManager: PreferenceManager,
) : SettingsRepository {
    override suspend fun saveAppTheme(theme: Int) {
        preferenceManager.setInt(key = PreferenceManager.APP_THEME, value = theme)
    }

    override fun getAppTheme() : Flow<Int?> {
        return preferenceManager.getIntFlow(key = PreferenceManager.APP_THEME)
    }

}