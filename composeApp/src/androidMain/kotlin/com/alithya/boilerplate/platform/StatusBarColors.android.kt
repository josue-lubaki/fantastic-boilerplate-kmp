package com.alithya.boilerplate.platform

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * created by Josue Lubaki
 * date : 2023-12-04
 * version : 1.0.0
 */
@Composable
actual fun StatusBarColors(
    statusBarColor: Color,
    navBarColor: Color
) {
    val sysUiController = rememberSystemUiController()
    SideEffect {
        sysUiController.setSystemBarsColor(color = statusBarColor)
        sysUiController.setNavigationBarColor(color = navBarColor)
    }
}