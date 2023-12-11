package com.alithya.boilerplate.features.login.data.models

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse (
    val token_type : String?,
    val expires_in : Long?,
    val access_token : String?,
    val refresh_token : String?
)