package com.alithya.boilerplate.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.alithya.boilerplate.features.settings.SettingsScreen
import com.alithya.boilerplate.platform.StatusBarColors

/**
 * created by Josue Lubaki
 * date : 2023-12-04
 * version : 1.0.0
 */

internal sealed class NavigationTab {
    internal object HomeTab : Tab {
        override val options: TabOptions
            @Composable
            get() {
                val title = "Home"
                val icon = rememberVectorPainter(Icons.Default.Home)

                return remember {
                    TabOptions(
                        index = 0u,
                        title = title,
                        icon = icon,
                    )
                }
            }

        @Composable
        override fun Content() {
//            HomeScreen()
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.error.copy(alpha = 0.5f)),
                contentAlignment = Alignment.Center
            ){
                StatusBarColors(
                    statusBarColor = MaterialTheme.colorScheme.background,
                    navBarColor = MaterialTheme.colorScheme.background,
                )
                Text(
                    text = "Home Screen",
                    color = MaterialTheme.colorScheme.onError,
                )
            }
        }
    }

    internal object SearchTab : Tab {
        override val options: TabOptions
            @Composable
            get() {
                val title = "Search"
                val icon = rememberVectorPainter(Icons.Default.Search)

                return remember {
                    TabOptions(
                        index = 1u,
                        title = title,
                        icon = icon,
                    )
                }
            }

        @Composable
        override fun Content() {
//            SearchScreen()
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)),
                contentAlignment = Alignment.Center
            ){
                StatusBarColors(
                    statusBarColor = MaterialTheme.colorScheme.background,
                    navBarColor = MaterialTheme.colorScheme.background,
                )
                Text(
                    text = "Search Screen",
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            }
        }
    }

    internal object AddTab : Tab {
        override val options: TabOptions
            @Composable
            get() {
                val title = "Add"
                val icon = rememberVectorPainter(Icons.Default.Add)

                return remember {
                    TabOptions(
                        index = 2u,
                        title = title,
                        icon = icon,
                    )
                }
            }

        @Composable
        override fun Content() {
//            AddScreen()
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.onErrorContainer.copy(alpha = 0.5f)),
                contentAlignment = Alignment.Center
            ){
                StatusBarColors(
                    statusBarColor = MaterialTheme.colorScheme.background,
                    navBarColor = MaterialTheme.colorScheme.background,
                )
                Text(
                    text = "Add Screen",
                    color = MaterialTheme.colorScheme.onError,
                )
            }
        }
    }

    internal object ProfileTab : Tab {
        override val options: TabOptions
            @Composable
            get() {
                val title = "Profile"
                val icon = rememberVectorPainter(Icons.Default.Person)

                return remember {
                    TabOptions(
                        index = 3u,
                        title = title,
                        icon = icon,
                    )
                }
            }

        @Composable
        override fun Content() {
//            ProfileScreen()
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f)),
                contentAlignment = Alignment.Center
            ){
                StatusBarColors(
                    statusBarColor = MaterialTheme.colorScheme.background,
                    navBarColor = MaterialTheme.colorScheme.background,
                )
                Text(
                    text = "Profile Screen",
                    color = MaterialTheme.colorScheme.onSecondary,
                )
            }
        }
    }

    internal object SettingsTab : Tab {
        override val options: TabOptions
            @Composable
            get() {
                val title = "Settings"
                val icon = rememberVectorPainter(Icons.Default.Settings)

                return remember {
                    TabOptions(
                        index = 4u,
                        title = title,
                        icon = icon,
                    )
                }
            }

        @Composable
        override fun Content() {
            SettingsScreen()
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(MaterialTheme.colorScheme.tertiary.copy(alpha = 0.5f)),
//                contentAlignment = Alignment.Center
//            ){
//                StatusBarColors(
//                    statusBarColor = MaterialTheme.colorScheme.background,
//                    navBarColor = MaterialTheme.colorScheme.background,
//                )
//                Text(
//                    text = "Settings Screen",
//                    color = MaterialTheme.colorScheme.onTertiary,
//                )
//            }
        }
    }

}