package com.alithya.boilerplate.features.login.presentation

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.alithya.boilerplate.features.login.domain.models.LoginForm
import com.alithya.boilerplate.features.login.domain.models.LoginStatus
import com.alithya.boilerplate.features.login.domain.usecases.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay

/**
 * created by josuelubaki
 * date : 2023-12-06
 * version : 1.0.0
 */

class LoginViewModel(
    private val useCase: LoginUseCase,
    private val dispatcher: CoroutineDispatcher,
) : ScreenModel {

    private val _state = MutableStateFlow<LoginState>(LoginState.Idle)
    val state: StateFlow<LoginState> = _state.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnLogin -> loginRequest(event.email, event.password)
        }
    }

    private fun loginRequest(email : String, password : String) {
        _state.value = LoginState.Loading
        try {
            screenModelScope.launch(dispatcher) {
                when (val response = useCase(LoginForm(email, password))) {
                    is LoginStatus.Success -> {
                        _state.value = LoginState.Success
                    }
                    is LoginStatus.Error -> {
                        _state.value = LoginState.Error(response.exception)
                    }
                }
            }
        } catch (e: Exception) {
            _state.value = LoginState.Error(e.message ?: "Unknown error")
        }
    }
}

