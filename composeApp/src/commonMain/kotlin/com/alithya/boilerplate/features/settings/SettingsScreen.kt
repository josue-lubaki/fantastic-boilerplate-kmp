package com.alithya.boilerplate.features.settings

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.alithya.boilerplate.core.presentation.components.BoilerPlateTopAppBar
import com.alithya.boilerplate.core.presentation.theme.BoilerPlateTheme
import com.alithya.boilerplate.platform.StatusBarColors
import org.koin.compose.koinInject

/**
 * created by Josue Lubaki
 * date : 2023-12-05
 * version : 1.0.0
 */

@Composable
fun SettingsScreen(
    settingsViewModel : SettingsScreenModel = koinInject()
) {

    val darkTheme = when (settingsViewModel.appTheme.collectAsState().value) {
        1 -> true
        else -> false
    }

    StatusBarColors(
        statusBarColor = MaterialTheme.colorScheme.background,
        navBarColor = MaterialTheme.colorScheme.background,
    )

    SettingsScreenContent(
        darkTheme = darkTheme,
        onDarkThemeChange = { themeValue ->
            settingsViewModel.setAppTheme(if (themeValue) 1 else 0)
        },
        optionsOpened = settingsViewModel.optionsOpened,
        openOptions = { option ->
            settingsViewModel.openOptions(option)
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreenContent(
    darkTheme: Boolean,
    onDarkThemeChange: (Boolean) -> Unit,
    optionsOpened: List<String>,
    openOptions: (String) -> Unit,
) {
    BoilerPlateTheme (
        darkTheme = darkTheme
    ) {
        Scaffold(
            topBar = {
                BoilerPlateTopAppBar(
                    hasBackNavigation = false,
                ) {
                    Text(text = "Settings")
                }
            },
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                item {
                    ThemeSetting(
                        darkTheme = darkTheme,
                        onDarkThemeChange = onDarkThemeChange,
                        expanded = { title ->
                            optionsOpened.contains(title)
                        },
                        onExpand = { title ->
                            openOptions(title)
                        },
                    )
                }
            }
        }
    }
}

@Composable
fun ThemeSetting(
    darkTheme: Boolean,
    onDarkThemeChange: (Boolean) -> Unit,
    onExpand: (String) -> Unit,
    expanded: (String) -> Boolean,
) {
    SettingCard(
        onExpand = {
            onExpand("Theme")
        },
        expanded = expanded("Theme"),
        title = "Theme",
        icon = Icons.Outlined.LightMode,
        content = {
            AutoStartSession(
                title = "App Theme (${
                    if (darkTheme) {
                        "Dark"
                    } else {
                        "Light"
                    }
                })",
                checked = darkTheme,
                onCheckedChange = {
                    onDarkThemeChange(it)
                },
            )
        },
    )
}

@Composable
fun AutoStartSession(
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = title)
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingCard(
    title: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
    onExpand: () -> Unit,
    expanded: Boolean,
) {
    Card(
        modifier = modifier,
        onClick = {
            onExpand()
        },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
            contentColor = MaterialTheme.colorScheme.surface,
        )
    ) {
        Column(
            modifier = modifier.padding(16.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = title,
                    )
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleLarge,
                    )
                }

                IconButton(onClick = { onExpand() }) {
                    Icon(
                        imageVector = if (expanded) {
                            Icons.Rounded.KeyboardArrowUp
                        } else {
                            Icons.Rounded.KeyboardArrowDown
                        },
                        contentDescription = null,
                    )
                }
            }
            AnimatedVisibility(expanded) {
                Column {
                    Spacer(modifier = Modifier.height(8.dp))
                    content()
                }
            }
        }
    }
}
