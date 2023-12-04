package com.alithya.boilerplate.platform

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

/**
 * created by Josue Lubaki
 * date : 2023-12-04
 * version : 1.0.0
 */

@Composable
expect fun font(name: String, res: String, weight: FontWeight, style: FontStyle): Font