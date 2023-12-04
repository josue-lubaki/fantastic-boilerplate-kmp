package com.alithya.boilerplate.core.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.alithya.boilerplate.platform.font

/**
 * created by Josue Lubaki
 * date : 2023-12-04
 * version : 1.0.0
 */

@Composable
internal fun getTypography(): Typography {
    val sfProRegular =
        font(
            "SF_Pro_Regular",
            "sf_pro_regular",
            FontWeight.Normal,
            FontStyle.Normal,
        )

    val sfProBold =
        font(
            "SF_Pro_Bold",
            "sf_pro_bold",
            FontWeight.Bold,
            FontStyle.Normal,
        )

    val sfProLight =
        font(
            "SF_Pro_Light",
            "sf_pro_light",
            FontWeight.Light,
            FontStyle.Normal,
        )

    val sfProMedium =
        font(
            "SF_Pro_Medium",
            "sf_pro_medium",
            FontWeight.Medium,
            FontStyle.Normal,
        )

    val sfProSemiBold =
        font(
            "SF_Pro_SemiBold",
            "sf_pro_semibold",
            FontWeight.SemiBold,
            FontStyle.Normal,
        )

    val sfProThin =
        font(
            "SF_Pro_Thin",
            "sf_pro_thin",
            FontWeight.Thin,
            FontStyle.Normal,
        )

    @Composable
    fun sfPro() = FontFamily(
        sfProThin,
        sfProLight,
        sfProRegular,
        sfProMedium,
        sfProSemiBold,
        sfProBold
    )

    return Typography(
        bodyLarge = TextStyle(
            fontFamily = sfPro(),
            fontWeight = FontWeight(510),
            fontSize = 18.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.15.sp
        ),
        bodyMedium = TextStyle(
            fontFamily = sfPro(),
            fontWeight = FontWeight(510),
            fontSize = 16.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.15.sp
        ),
        bodySmall = TextStyle(
            fontFamily = sfPro(),
            fontWeight = FontWeight(510),
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.4.sp
        ),
        titleLarge = TextStyle(
            fontFamily = sfPro(),
            fontWeight = FontWeight(700),
            fontSize = 24.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.15.sp
        ),
        titleMedium = TextStyle(
            fontFamily = sfPro(),
            fontWeight = FontWeight(700),
            fontSize = 20.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.15.sp,
        ),
        titleSmall = TextStyle(
            fontFamily = sfPro(),
            fontWeight = FontWeight(700),
            fontSize = 16.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp,
        ),
        labelLarge = TextStyle(
            fontFamily = sfPro(),
            fontWeight = FontWeight(500),
            fontSize = 16.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp,
        ),
        labelMedium = TextStyle(
            fontFamily = sfPro(),
            fontWeight = FontWeight(500),
            fontSize = 14.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp,
        ),
        labelSmall = TextStyle(
            fontFamily = sfPro(),
            fontWeight = FontWeight(500),
            fontSize = 12.sp,
            lineHeight = 12.sp,
            letterSpacing = 0.4.sp,
        ),
        displayLarge = TextStyle(
            fontFamily = sfPro(),
            fontWeight = FontWeight.Bold,
            fontSize = 57.sp,
            lineHeight = 64.sp,
        ),
        displayMedium = TextStyle(
            fontFamily = sfPro(),
            fontWeight = FontWeight.Bold,
            fontSize = 48.sp,
            lineHeight = 48.sp,
        ),
        displaySmall = TextStyle(
            fontFamily = sfPro(),
            fontWeight = FontWeight.Bold,
            fontSize = 36.sp,
            lineHeight = 40.sp,
        ),
        headlineLarge = TextStyle(
            fontFamily = sfPro(),
            fontWeight = FontWeight(700),
            fontSize = 24.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.15.sp,
        ),
    )
}