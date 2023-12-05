package com.alithya.boilerplate.features.settings

import androidx.compose.runtime.mutableStateListOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.alithya.boilerplate.core.domain.repository.settings.SettingsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * created by Josue Lubaki
 * date : 2023-12-05
 * version : 1.0.0
 */

class SettingsScreenModel(
    private val settingsRepository : SettingsRepository
) : ScreenModel {

    val appTheme: StateFlow<Int?> = settingsRepository.getAppTheme()
        .map { it }
        .stateIn(
            scope = screenModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = null,
        )

    fun setAppTheme(appTheme: Int) {
        screenModelScope.launch {
            settingsRepository.saveAppTheme(appTheme)
        }
    }

    val optionsOpened = mutableStateListOf("")
    fun openOptions(option: String) {
        if (optionsOpened.contains(option)) {
            optionsOpened.remove(option)
        } else {
            optionsOpened.add(option)
        }
    }

}