package com.alithya.boilerplate.core.presentation.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

/**
 * created by Josue Lubaki
 * date : 2023-12-04
 * version : 1.0.0
 */

@Composable
fun FilledIcon(item: Tab) = when (item.options.index) {
    (0u).toUShort() -> rememberVectorPainter(Icons.Default.Home)
    (1u).toUShort() -> rememberVectorPainter(Icons.Default.Search)
    (2u).toUShort() -> rememberVectorPainter(Icons.Default.Add)
    (3u).toUShort() -> rememberVectorPainter(Icons.Default.Person)
    (4u).toUShort() -> rememberVectorPainter(Icons.Default.Settings)
    else -> rememberVectorPainter(Icons.Default.Home)
}