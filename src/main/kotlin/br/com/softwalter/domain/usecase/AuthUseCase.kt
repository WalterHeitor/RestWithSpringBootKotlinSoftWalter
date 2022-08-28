package br.com.softwalter.domain.usecase

import br.com.softwalter.presentation.users.AccountCredentialsRequest
import org.springframework.http.ResponseEntity

interface AuthUseCase {

    fun signin(data: AccountCredentialsRequest) : ResponseEntity<*>

    fun refreshToken(username: String, refreshToken: String): ResponseEntity<*>

}