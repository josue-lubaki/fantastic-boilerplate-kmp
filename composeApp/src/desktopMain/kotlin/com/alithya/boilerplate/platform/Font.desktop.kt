package com.alithya.boilerplate.platform

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font

/**
 * created by Josue Lubaki
 * date : 2023-12-04
 * version : 1.0.0
 */
@Composable
actual fun font(
    name: String,
    res: String,
    weight: FontWeight,
    style: FontStyle
): Font = Font("font/$res.ttf", weight, style)