package br.com.softwalter.presentation.users

data class AccountCredentialsRequest(
    val userName: String? = null,
    val password: String? = null,
)
