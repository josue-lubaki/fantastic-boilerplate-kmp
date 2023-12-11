package com.alithya.boilerplate.core.data.local.settings

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.Settings
import com.russhwolf.settings.coroutines.getBooleanFlow
import com.russhwolf.settings.coroutines.getIntFlow
import com.russhwolf.settings.coroutines.getIntOrNullFlow
import com.russhwolf.settings.set
import kotlinx.coroutines.flow.Flow
import com.russhwolf.settings.coroutines.getLongFlow
import com.russhwolf.settings.coroutines.getStringOrNullFlow

/**
 * created by Josue Lubaki
 * date : 2023-12-04
 * version : 1.0.0
 */

@OptIn(ExperimentalSettingsApi::class)
class PreferenceManager constructor(private val settings: Settings) {
    private val observableSettings: ObservableSettings by lazy { settings as ObservableSettings }

    companion object {
        const val APP_THEME = "app_theme"
        const val ACCESS_TOKEN = "access_token"
    }

    // Strings
    fun setString(key: String, value: String) {
        observableSettings.set(key = key, value = value)
    }
    fun getString(key: String) : String = observableSettings.getString(key = key, defaultValue = "")

    fun getStringFlow(key: String): Flow<String?> {
        return observableSettings.getStringOrNullFlow(key = key)
    }

    fun getStringFlowOrNull(key: String) : Flow<String?> = observableSettings.getStringOrNullFlow(key = key)

    // Int
    fun setInt(key: String, value: Int) {
        observableSettings.set(key = key, value = value)
    }

    fun getIntFlow(key: String) : Flow<Int> = observableSettings.getIntFlow(
        key = key,
        defaultValue = 0,
    )

    fun getIntFlowOrNull(key: String) : Flow<Int?> = observableSettings.getIntOrNullFlow(key = key)


    // Boolean
    fun setBoolean(key: String, value: Boolean) {
        observableSettings.set(key = key, value = value)
    }
    fun getBooleanFlow(key: String) : Flow<Boolean?> {
        return observableSettings.getBooleanFlow(
            key = key,
            defaultValue = false,
        )
    }

    // Long
    fun setLong(key: String, value: Long) {
        observableSettings.set(key = key, value = value)
    }
    fun getLongFlow(key: String) : Flow<Long?>{
        return observableSettings.getLongFlow(
            key = key,
            defaultValue = 0,
        )
    }

    // clear all settings
    fun clearPreferences() {
        observableSettings.clear()
    }

}