package com.alithya.boilerplate.core.presentation.theme

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

/**
 * created by Josue Lubaki
 * date : 2023-12-04
 * version : 1.0.0
 */

val shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(8.dp),
    large = RoundedCornerShape(16.dp),
    extraLarge = RoundedCornerShape(20.dp)
)

val RoundedCornerShapeTop: CornerBasedShape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)
val ButtonRoundedCornerShape : RoundedCornerShape = RoundedCornerShape(5.dp)