package com.alithya.boilerplate.core.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.alithya.boilerplate.core.presentation.theme.ButtonRoundedCornerShape
import com.alithya.boilerplate.core.presentation.theme.dimensions

/**
 * created by Josue Lubaki
 * date : 2023-12-08
 * version : 1.0.0
 */

@Composable
private fun ButtonBase(
    modifier : Modifier = Modifier,
    text : String,
    testTag : String,
    textColor : Color,
    containerColor : Color,
    contentColor : Color,
    enabled : Boolean = true,
    textStyle : TextStyle = MaterialTheme.typography.labelMedium,
    fontWeight: FontWeight = FontWeight(700),
    contentPadding : PaddingValues = PaddingValues(MaterialTheme.dimensions.medium),
    shape: Shape = ButtonRoundedCornerShape,
    border : BorderStroke? = null,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .testTag(testTag),
        contentPadding = contentPadding,
        enabled= enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        ),
        shape = shape,
        border = border,
    ) {
        Text(
            modifier = Modifier.testTag("$testTag-text"),
            text = text,
            style = textStyle,
            color = if(enabled) textColor else MaterialTheme.colorScheme.onSurfaceVariant,
            fontWeight = fontWeight
        )
    }
}

@Composable
fun ButtonPrimary(
    modifier : Modifier = Modifier,
    text : String,
    testTag : String,
    enabled : Boolean = true,
    textStyle : TextStyle = MaterialTheme.typography.labelMedium,
    fontWeight: FontWeight = FontWeight(700),
    contentPadding : PaddingValues = PaddingValues(MaterialTheme.dimensions.medium),
    shape: Shape = ButtonRoundedCornerShape,
    onClick: () -> Unit,
) {
    ButtonBase(
        modifier = modifier,
        text = text,
        testTag = "ButtonPrimary-$testTag",
        textColor =
        if(isSystemInDarkTheme()) MaterialTheme.colorScheme.onPrimary
        else MaterialTheme.colorScheme.secondaryContainer,
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        enabled = enabled,
        textStyle = textStyle,
        fontWeight = fontWeight,
        contentPadding = contentPadding,
        shape = shape,
        border = BorderStroke(
            width = 2.dp,
            color =
            if(enabled) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.surfaceVariant
        ),
        onClick = onClick
    )
}

@Composable
fun ButtonSecondary(
    modifier : Modifier = Modifier,
    text : String,
    testTag : String,
    enabled : Boolean = true,
    textStyle : TextStyle = MaterialTheme.typography.labelMedium,
    fontWeight: FontWeight = FontWeight(700),
    contentPadding : PaddingValues = PaddingValues(MaterialTheme.dimensions.medium),
    shape: Shape = ButtonRoundedCornerShape,
    onClick: () -> Unit = {},
) {
    ButtonBase(
        modifier = modifier,
        text = text,
        testTag = "ButtonSecondary-$testTag",
        textColor =
        if(isSystemInDarkTheme()) MaterialTheme.colorScheme.onSecondaryContainer
        else MaterialTheme.colorScheme.primary,
        containerColor = MaterialTheme.colorScheme.onPrimary,
        contentColor = MaterialTheme.colorScheme.primary,
        enabled = enabled,
        textStyle = textStyle,
        fontWeight = fontWeight,
        contentPadding = contentPadding,
        shape = shape,
        border = BorderStroke(
            width = 2.dp,
            color =
            if(enabled) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.surfaceVariant
        ),
        onClick = onClick
    )
}

@Composable
fun ButtonLogout(
    modifier : Modifier = Modifier,
    text : String,
    testTag : String,
    enabled : Boolean = true,
    containerColor : Color =  MaterialTheme.colorScheme.onError,
    textStyle : TextStyle = MaterialTheme.typography.labelMedium,
    fontWeight: FontWeight = FontWeight(700),
    contentPadding : PaddingValues = PaddingValues(MaterialTheme.dimensions.medium),
    shape: Shape = ButtonRoundedCornerShape,
    onClick: () -> Unit = {},
) {
    ButtonBase(
        modifier = modifier,
        text = text,
        testTag = "ButtonLogout-$testTag",
        textColor =
        if(isSystemInDarkTheme()) MaterialTheme.colorScheme.error
        else MaterialTheme.colorScheme.error,
        containerColor = containerColor,
        contentColor = MaterialTheme.colorScheme.error,
        enabled = enabled,
        textStyle = textStyle,
        fontWeight = fontWeight,
        contentPadding = contentPadding,
        shape = shape,
        border = BorderStroke(
            width = 1.dp,
            color =
            if(enabled) MaterialTheme.colorScheme.error
            else MaterialTheme.colorScheme.surfaceVariant
        ),
        onClick = onClick
    )
}

@Composable
fun ButtonBack(
    modifier : Modifier = Modifier,
    text : String,
    textColor : Color = MaterialTheme.colorScheme.primary,
    textStyle : TextStyle = MaterialTheme.typography.labelLarge,
    textDecoration : TextDecoration = TextDecoration.Underline,
    icon : ImageVector = Icons.Default.ArrowBackIosNew,
    iconColor : Color = MaterialTheme.colorScheme.primary,
    testTag : String,
    onClick : () -> Unit,
) {
    Row(
        modifier = modifier
            .testTag("ButtonBack-$testTag")
            .clickable{ onClick() },
        verticalAlignment = Alignment.CenterVertically,
    ){
        Icon(
            modifier = Modifier
                .testTag("ButtonBack-$testTag-icon")
                .padding(end = 4.dp),
            imageVector = icon,
            contentDescription = "Back",
            tint = iconColor,
        )
        Text(
            modifier= Modifier.testTag("ButtonBack-$testTag-text"),
            text = text,
            color = textColor,
            style = textStyle,
            textDecoration = textDecoration
        )
    }
}