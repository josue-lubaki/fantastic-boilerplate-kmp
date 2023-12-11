package com.alithya.boilerplate.features.login.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.alithya.boilerplate.core.presentation.components.*
import com.alithya.boilerplate.core.presentation.theme.RoundedCornerShapeTop
import com.alithya.boilerplate.core.presentation.theme.dimensions
import com.alithya.boilerplate.main.MainScreen
import com.alithya.boilerplate.platform.StatusBarColors
import io.github.aakira.napier.Napier
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject


class LoginScreen : Screen {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    @Composable
    override fun Content() {

        val loginViewModel: LoginViewModel = koinInject()

        val navigator = LocalNavigator.current!!
        val windowSizeClass = calculateWindowSizeClass()
        val useNavRail = windowSizeClass.widthSizeClass > WindowWidthSizeClass.Compact

        val state by loginViewModel.state.collectAsState()
        val errors = rememberSaveable { mutableStateOf<String?>(null) }

        val onNavigateToRegister = {
            navigator.push(MainScreen())
        }

        val onSignInClickHandler = { email: String, password: String ->
            errors.value = null
            loginViewModel.onEvent(LoginEvent.OnLogin(email, password))
        }

        val onForgotPasswordClickHandler = {
            Napier.d("Forgot password clicked")
        }

        LaunchedEffect(key1 = state) {
            when (val currentState = state) {
                is LoginState.Success -> {
                    Napier.d("Successfully logged in, navigate to App")
                    navigator.push(MainScreen())
                }

                is LoginState.Error -> {
                    Napier.d("104 - com.alithya.boilerplate.features.login.presentation.LoginScreen cause : ${currentState.message}")
                    errors.value = currentState.message
                }

                else -> Unit
            }
        }

        StatusBarColors(
            statusBarColor = MaterialTheme.colorScheme.background,
            navBarColor = MaterialTheme.colorScheme.background,
        )

        LoginContent(
            useNavRail = useNavRail,
            state = state,
            errors = errors.value,
            onNavigateToRegister = onNavigateToRegister,
            onForgotPasswordClickHandler = onForgotPasswordClickHandler,
            onSignInClickHandler = onSignInClickHandler,
        )
    }
}

@Composable
private fun LoginContent(
    useNavRail: Boolean,
    state : LoginState,
    onNavigateToRegister: () -> Unit,
    onForgotPasswordClickHandler: () -> Unit,
    onSignInClickHandler: (String, String) -> Unit,
    errors : String?
) {
    if(useNavRail){
        LargeScreen(
            state = state,
            loginErrorMessage = errors,
            onNavigateToRegister = onNavigateToRegister,
            onForgotPasswordClicked = onForgotPasswordClickHandler,
            onSignInClicked = onSignInClickHandler
        )
    }
    else {
        SmallScreen(
            state = state,
            loginErrorMessage = errors,
            onNavigateToRegister = onNavigateToRegister,
            onForgotPasswordClicked = onForgotPasswordClickHandler,
            onSignInClicked = onSignInClickHandler
        )
    }
}

@Composable
private fun SmallScreen(
    state : LoginState,
    loginErrorMessage: String?,
    onNavigateToRegister: () -> Unit,
    onForgotPasswordClicked: () -> Unit,
    onSignInClicked: (String, String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onBackground)
    ) {
        Brand(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.dimensions.semiLarge)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShapeTop)
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Forms(
                loginErrorMessage = loginErrorMessage,
                onNavigateToRegister = onNavigateToRegister,
                onForgotPasswordClicked = onForgotPasswordClicked,
                onSignInClicked = onSignInClicked
            )
        }
    }
    CircularProgressBar(state is LoginState.Loading)
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun LargeScreen(
    state : LoginState,
    loginErrorMessage: String?,
    onNavigateToRegister: () -> Unit,
    onForgotPasswordClicked: () -> Unit,
    onSignInClicked: (String, String) -> Unit,
) {

    Row(
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.primary)
                .weight(1f)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource("ic_background.png"),
                contentDescription = "background logo"
            )

            Brand(
                modifier = Modifier.fillMaxSize(),
                isLandScape = true
            )
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .background(MaterialTheme.colorScheme.background)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = MaterialTheme.dimensions.paddingSemiXLarge,
                        start = MaterialTheme.dimensions.paddingXLarge,
                        end = MaterialTheme.dimensions.paddingMedium,
                    )
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Forms(
                    loginErrorMessage = loginErrorMessage,
                    isLandScape = true,
                    onNavigateToRegister = onNavigateToRegister,
                    onForgotPasswordClicked = onForgotPasswordClicked,
                    onSignInClicked = onSignInClicked,
                )
                Column(
                    modifier = Modifier
                        .padding(
                            top = MaterialTheme.dimensions.paddingLargeExtra,
                            start = MaterialTheme.dimensions.medium,
                            end = MaterialTheme.dimensions.medium,
                            bottom = MaterialTheme.dimensions.paddingSemiXLarge,
                        ),
                    verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.paddingSmall),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "© Copyright Alithya - 2022".uppercase(),
                        style = MaterialTheme.typography.labelMedium,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "Privacy Policy | Terms of use | Accessibility Statement".uppercase(),
                        style = MaterialTheme.typography.labelMedium,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            CircularProgressBar(state is LoginState.Loading)
        }
    }
    
}

@Composable
private fun Forms(
    loginErrorMessage: String?,
    isLandScape: Boolean = false,
    onNavigateToRegister: () -> Unit,
    onForgotPasswordClicked: () -> Unit,
    onSignInClicked: (String, String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = MaterialTheme.dimensions.xlarge,
                vertical = MaterialTheme.dimensions.medium
            ),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.semiLarge),
        ) {
            val email = rememberSaveable { mutableStateOf("josue.lubaki@alithya.com") }
            val password = rememberSaveable { mutableStateOf("Josue2022@") }

            Text(
                modifier = Modifier
                    .padding(top = MaterialTheme.dimensions.micro)
                    .testTag("loginTitleQa"),
                text = "Log In",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.displayMedium,
                )

            if (loginErrorMessage != null) {
                AlertMessage(message = loginErrorMessage)
            }

            TextFieldCreator(
                value = email.value,
                onValueChange = { email.value = it },
                titleName = "Email",
                placeholder = "name.lastname@alithya.com",
                testTag = "loginEmailQa",
                isError = loginErrorMessage != null,
            )

            PasswordAndForgotPassword(
                password = password,
                hasError = loginErrorMessage != null,
                onPasswordChange = { password.value = it },
                onForgotPasswordClicked = { onForgotPasswordClicked() }
            )

            ButtonPrimary(
                modifier =
            if (!isLandScape) {
                Modifier.padding(top = MaterialTheme.dimensions.medium)
            } else {
                Modifier
                    .width(MaterialTheme.dimensions.buttonWidthDefault)
                    .padding(top = MaterialTheme.dimensions.medium)
                   },
                text = "LOGIN",
                testTag = "loginSignInButtonQa",
                onClick = { onSignInClicked(email.value, password.value) }
            )

            Register(
                onNavigateToRegister = onNavigateToRegister,
                isLandScape = isLandScape,
                hasError = loginErrorMessage != null,
            )
    }
}

@Composable
private fun PasswordAndForgotPassword(
    password: MutableState<String>,
    hasError: Boolean,
    onPasswordChange: (String) -> Unit,
    onForgotPasswordClicked: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.semiLarge)
    ) {
        TextFieldCreator(
            modifier = Modifier.width(MaterialTheme.dimensions.textFieldWidthMedium),
            value = password.value,
            onValueChange = onPasswordChange,
            titleName = "Password",
            placeholder = "••••••",
            testTag = "loginPasswordQa",
            isError = hasError,
            isPassword = true,
            titleColor = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = "Forgot Password",
            modifier = Modifier
                .testTag("loginForgotPassword")
                .clickable { onForgotPasswordClicked() },
            style = MaterialTheme.typography.labelMedium,
            lineHeight = MaterialTheme.dimensions.fontSizeXXLarge,
            fontWeight = FontWeight(700),
            textDecoration = TextDecoration.Underline,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
private fun Register(
    onNavigateToRegister: () -> Unit,
    isLandScape: Boolean = false,
    hasError: Boolean,
) {
    Column(
        modifier = Modifier.padding(top = if (!hasError) MaterialTheme.dimensions.large else MaterialTheme.dimensions.small),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.paddingMicro)
    ) {
        Text(
            modifier = Modifier.testTag("loginRegisterQa-title"),
            text = "You don't have an account yet ?",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground
        )

        ButtonSecondary(
            modifier =
            if (isLandScape) {
                Modifier
                    .width(MaterialTheme.dimensions.buttonWidthDefault)
                    .padding(top = MaterialTheme.dimensions.small)
            } else Modifier,
            text = "REGISTER",
            testTag = "loginRegisterButtonQa",
            onClick = onNavigateToRegister
        )
    }
}