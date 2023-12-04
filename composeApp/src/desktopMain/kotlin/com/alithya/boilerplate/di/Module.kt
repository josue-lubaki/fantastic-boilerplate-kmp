package com.alithya.boilerplate.di

import org.koin.core.module.Module
import org.koin.dsl.module
import com.alithya.boilerplate.platform.MultiplatformSettingsWrapper

/**
 * created by Josue Lubaki
 * date : 2023-12-04
 * version : 1.0.0
 */

actual fun platformModule(): Module = module {
    single { MultiplatformSettingsWrapper().createSettings() }
}