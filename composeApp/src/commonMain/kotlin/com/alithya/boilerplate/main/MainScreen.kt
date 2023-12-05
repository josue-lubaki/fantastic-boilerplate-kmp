package com.alithya.boilerplate.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults.elevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.alithya.boilerplate.core.presentation.components.NavigationRailBar
import com.alithya.boilerplate.core.presentation.components.NavigationTab
import com.alithya.boilerplate.core.presentation.utils.FilledIcon

/**
 * created by Josue Lubaki
 * date : 2023-12-04
 * version : 1.0.0
 */

class MainScreen : Screen {

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    @Composable
    override fun Content() {
        val windowSizeClass = calculateWindowSizeClass()
        val useNavRail = windowSizeClass.widthSizeClass > WindowWidthSizeClass.Compact

        TabNavigator(
            NavigationTab.HomeTab,
        ) {
            val tabNavigator = LocalTabNavigator.current

            if (useNavRail) {
                Row {
                    NavigationRailBar(
                        tabNavigator = it,
                        navRailItems = listOf(
                            NavigationTab.HomeTab,
                            NavigationTab.SearchTab,
                            NavigationTab.AddTab,
                            NavigationTab.ProfileTab,
                            NavigationTab.SettingsTab,
                        ),
                    )
                    CurrentScreen()
                }
            } else {
                Scaffold(
                    content = { innerPadding ->
                        Box(
                            modifier = Modifier
                                .padding(innerPadding),
                        ) {
                            CurrentScreen()
                        }
                    },
                    floatingActionButtonPosition = FabPosition.Center,
                    floatingActionButton = {
                        val selectedTab = tabNavigator.current == NavigationTab.AddTab
                        FloatingActionButton(
                            modifier = Modifier
                                .offset(y = 60.dp)
                                .size(42.dp),
                            containerColor = if(selectedTab) {
                                MaterialTheme.colorScheme.secondaryContainer
                            } else {
                                MaterialTheme.colorScheme.primary
                            },
                            onClick = {
                                tabNavigator.current = NavigationTab.AddTab
                            },
                            elevation = elevation(
                                defaultElevation = 0.dp,
                            ),
                            shape = CircleShape,
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = "",
                                tint = if(selectedTab){
                                    MaterialTheme.colorScheme.secondary
                                }else {
                                    MaterialTheme.colorScheme.onPrimary

                               },
                                modifier = Modifier.size(24.dp),
                            )
                        }
                    },
                    bottomBar = {
                        BottomNavigation(
                            backgroundColor = MaterialTheme.colorScheme.background,
                        ) {
                            TabNavigationItem(NavigationTab.HomeTab)
                            TabNavigationItem(NavigationTab.SearchTab)
                            TabNavigationItem(NavigationTab.ProfileTab)
                            TabNavigationItem(NavigationTab.SettingsTab)
                        }
                    },
                )
            }
        }
    }
}

@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current
    val isSelected = tabNavigator.current == tab

    BottomNavigationItem(
        modifier = Modifier
            .offset(
                x = when (tab.options.index) {
                    (0u).toUShort() -> 0.dp
                    (1u).toUShort() -> (-24).dp
                    (3u).toUShort() -> 24.dp
                    (4u).toUShort() -> 0.dp
                    else -> 0.dp
                },
            ),
        selected = tabNavigator.current.key == tab.key,
        onClick = {
            tabNavigator.current = tab
        },
        icon = {
            tab.options.icon?.let {
                Icon(
                    painter = if (isSelected) {
                        FilledIcon(tab)
                    } else {
                        it
                    },
                    contentDescription = tab.options.title,
                    tint = if (isSelected) {
                        MaterialTheme.colorScheme.secondary
                    } else {
                        MaterialTheme.colorScheme.onBackground
                    },
                    modifier = Modifier.then(
                        if (isSelected) {
                            Modifier
                                .background(
                                    color = MaterialTheme.colorScheme.secondaryContainer,
                                    shape = CircleShape,
                                )
                                .padding(10.dp)
                        } else {
                            Modifier
                        }
                    )
                )
            }
        },
    )
}