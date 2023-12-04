package com.alithya.boilerplate.platform

import com.russhwolf.settings.PreferencesSettings
import com.russhwolf.settings.Settings
import java.util.prefs.Preferences

/**
 * created by Josue Lubaki
 * date : 2023-12-04
 * version : 1.0.0
 */

actual class MultiplatformSettingsWrapper {
    actual fun createSettings(): Settings {
        val delegate: Preferences = Preferences.userRoot()
        return PreferencesSettings(delegate)
    }
}