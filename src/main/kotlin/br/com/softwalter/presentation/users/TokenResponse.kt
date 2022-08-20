package br.com.softwalter.presentation.users

import java.util.*

data class TokenResponse(
    val userName: String? = null,
    val autenticated: Boolean? = null,
    val created: Date? = null,
    val expiration: Date? = null,
    val accessToken: String? = null,
    val refreshToken: String? = null,
)
