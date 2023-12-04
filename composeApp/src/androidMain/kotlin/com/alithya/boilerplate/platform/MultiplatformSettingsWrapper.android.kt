package com.alithya.boilerplate.platform

import android.content.Context
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings

/**
 * created by Josue Lubaki
 * date : 2023-12-04
 * version : 1.0.0
 */

actual class MultiplatformSettingsWrapper(private val context: Context) {
    actual fun createSettings(): Settings {
        val delegate = context.getSharedPreferences("boilerPlate_preferences", Context.MODE_PRIVATE)
        return SharedPreferencesSettings(delegate)
    }
}