package com.alithya.boilerplate.core.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.alithya.boilerplate.core.presentation.theme.Black
import com.alithya.boilerplate.core.presentation.theme.Gray300
import com.alithya.boilerplate.core.presentation.theme.Primary15
import com.alithya.boilerplate.core.presentation.theme.dimensions

/**
 * created by Josue Lubaki
 * date : 2023-12-08
 * version : 1.0.0
 */

@Composable
fun TextFieldCreator(
    modifier : Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    testTag: String,
    titleName : String,
    titleColor : Color = MaterialTheme.colorScheme.onBackground,
    titleTextStyle: TextStyle = MaterialTheme.typography.labelLarge,
    placeholder: String = "",
    placeholderColor : Color = Primary15,
    contentColor : Color = Primary15,
    inputBackgroundColor : Color = Color.White,
    shape : Shape = RoundedCornerShape(MaterialTheme.dimensions.small),
    keyboardType : KeyboardType = KeyboardType.Text,
    singleLine: Boolean = true,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(MaterialTheme.dimensions.small),
    isError : Boolean = false,
    isPassword : Boolean = false,
    enabled : Boolean = true,
) {
    Column(
        modifier = modifier,
        verticalArrangement = verticalArrangement
    ) {
        Text(
            modifier = Modifier.testTag("$testTag-title"),
            text = titleName,
            style = titleTextStyle,
            color = titleColor,
            fontWeight = FontWeight(700)
        )
        if(!isPassword){
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("$testTag-input"),
                value = value,
                onValueChange = onValueChange,
                textStyle= TextStyle(
                    color = contentColor,
                    fontSize = MaterialTheme.dimensions.fontSizeDefault,
                    fontWeight= FontWeight.Normal,
                ),
                placeholder = {
                    Text(
                        text = placeholder,
                        color = placeholderColor,
                        fontSize = MaterialTheme.dimensions.fontSizeDefault,
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = contentColor,
                    focusedContainerColor = inputBackgroundColor,
                    unfocusedContainerColor = inputBackgroundColor,
                    disabledContainerColor = inputBackgroundColor,
                    focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    unfocusedIndicatorColor = Black,
                ),
                shape = shape,
                keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
                singleLine = singleLine,
                isError = isError,
                enabled = enabled,
            )
        }
        else {
            val showPasswordState = rememberSaveable { mutableStateOf(false) }
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("$testTag-input"),
                value = value,
                onValueChange = onValueChange,
                textStyle= TextStyle(
                    fontWeight= FontWeight.Normal,
                ),
                placeholder = {
                    Text(
                        text = placeholder,
                        color = placeholderColor,
                        fontSize = MaterialTheme.dimensions.fontSizeDefault,                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = contentColor,
                    focusedContainerColor = inputBackgroundColor,
                    unfocusedContainerColor = inputBackgroundColor,
                    disabledContainerColor = inputBackgroundColor,
                    focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    unfocusedIndicatorColor = Black,
                ),
                shape = shape,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = if (showPasswordState.value) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                trailingIcon = {
                    IconButton(
                        modifier = Modifier.testTag("$testTag-IconButton"),
                        onClick = {
                            showPasswordState.value = !showPasswordState.value
                        }
                    ) {
                        Icon(
                            imageVector =
                            if(showPasswordState.value) Icons.Filled.VisibilityOff
                            else Icons.Filled.Visibility,
                            contentDescription = null,
                            tint = Gray300
                        )
                    }
                },
                singleLine = singleLine,
                isError = isError,
            )
        }
    }
}