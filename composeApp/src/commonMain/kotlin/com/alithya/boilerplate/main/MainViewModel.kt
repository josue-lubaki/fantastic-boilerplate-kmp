package com.alithya.boilerplate.main

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.alithya.boilerplate.core.domain.repository.settings.SettingsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

/**
 * created by Josue Lubaki
 * date : 2023-12-04
 * version : 1.0.0
 */

class MainViewModel(
    settingsRepository: SettingsRepository
) : ScreenModel {
    val appTheme: StateFlow<Int?> = settingsRepository.getAppTheme().map { it }.stateIn(
        scope = screenModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = null,
    )
}