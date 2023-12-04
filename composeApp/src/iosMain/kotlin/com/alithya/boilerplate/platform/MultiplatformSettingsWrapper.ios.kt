package com.alithya.boilerplate.platform

import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import platform.Foundation.NSUserDefaults

/**
 * created by Josue Lubaki
 * date : 2023-12-04
 * version : 1.0.0
 */

actual class MultiplatformSettingsWrapper {
    actual fun createSettings(): Settings {
        val delegate = NSUserDefaults.standardUserDefaults
        return NSUserDefaultsSettings(delegate)
    }
}