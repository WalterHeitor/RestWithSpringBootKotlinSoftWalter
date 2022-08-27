package br.com.softwalter.presentation.auth

import br.com.softwalter.domain.usecase.AuthUseCase
import br.com.softwalter.presentation.users.AccountCredentialsRequest
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Authentication Endpoint")
@RestController
@RequestMapping("/auth")
class AuthController {
    @Autowired
    lateinit var authUseCase: AuthUseCase

    @Operation(summary = "Autenticacao de usuarios e token")
    @PostMapping(value = ["/singin"])
    fun singin (@RequestBody data: AccountCredentialsRequest?) : ResponseEntity<*>? {



        return if (data!!.userName.isNullOrBlank() || data.password.isNullOrBlank())
            ResponseEntity.status(HttpStatus.FORBIDDEN).body("Requsicao do cliente invalida")
            else authUseCase.signin(data)
    }
}