package com.alithya.boilerplate.platform

import com.russhwolf.settings.Settings

/**
 * created by Josue Lubaki
 * date : 2023-12-04
 * version : 1.0.0
 */

expect class MultiplatformSettingsWrapper {
    fun createSettings(): Settings
}