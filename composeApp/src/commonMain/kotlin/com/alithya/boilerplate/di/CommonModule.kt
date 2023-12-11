package com.alithya.boilerplate.di

import com.alithya.boilerplate.core.data.local.settings.PreferenceManager
import com.alithya.boilerplate.core.data.repository.settings.SettingsRepositoryImpl
import com.alithya.boilerplate.core.domain.repository.settings.SettingsRepository
import com.alithya.boilerplate.features.settings.SettingsScreenModel
import com.alithya.boilerplate.main.MainViewModel
import kotlinx.coroutines.CoroutineDispatcher
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * created by Josue Lubaki
 * date : 2023-12-04
 * version : 1.0.0
 */

fun commonModule() = module {

    /**
     * Multiplatform-Settings
     */
    single<PreferenceManager> {
        PreferenceManager(settings = get())
    }

    /**
     * Repositories
     */
    single<SettingsRepository> {
        SettingsRepositoryImpl(
            preferenceManager = get(),
        )
    }

    single<CoroutineDispatcher> { dispatcher() }

    /**
     * ViewModels
     */
    single<MainViewModel> {
        MainViewModel(
            settingsRepository = get(),
        )
    }

    single<SettingsScreenModel> {
        SettingsScreenModel(
            settingsRepository = get(),
        )
    }
}

expect fun platformModule(): Module
expect fun dispatcher() : CoroutineDispatcher