package com.alithya.boilerplate.core.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Report
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

/**
 * created by Josue Lubaki
 * date : 2023-12-08
 * version : 1.0.0
 */

@Composable
fun AlertMessage(
    modifier: Modifier = Modifier,
    message: String,
    icon: ImageVector = Icons.Default.Report,
    backgroundColor: Color = MaterialTheme.colorScheme.error,
    contentColor: Color = MaterialTheme.colorScheme.onError,
    iconColor: Color = MaterialTheme.colorScheme.onError,
    shape: Shape = RoundedCornerShape(8.dp),
    style: TextStyle = MaterialTheme.typography.labelLarge,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor,
            contentColor = contentColor
        ),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = shape
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                modifier = Modifier
                    .padding(10.dp),
                imageVector = icon,
                contentDescription = "warning icon",
                tint = iconColor
            )
            Text(
                modifier = modifier
                    .padding(10.dp),
                text = message,
                style = style,
                color = contentColor
            )
        }
    }
}