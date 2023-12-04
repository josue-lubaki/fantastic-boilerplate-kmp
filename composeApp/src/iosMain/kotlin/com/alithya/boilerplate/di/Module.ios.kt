package com.alithya.boilerplate.di

import org.koin.core.module.Module
import org.koin.dsl.module
import com.alithya.boilerplate.platform.MultiplatformSettingsWrapper

actual fun platformModule(): Module = module {
    single { MultiplatformSettingsWrapper().createSettings() }
}