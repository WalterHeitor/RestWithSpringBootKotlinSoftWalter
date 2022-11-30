package br.com.softwalter.domain.usecase.impl

import br.com.softwalter.domain.model.rbac.Users
import br.com.softwalter.domain.repository.rbac.UsersRepository
import br.com.softwalter.domain.usecase.AuthUseCase
import br.com.softwalter.presentation.users.AccountCredentialsRequest
import br.com.softwalter.presentation.users.TokenResponse
import br.com.softwalter.security.jwt.JwtTokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class AuthUseCaseImpl(
    val userRepository: UsersRepository,
) : AuthUseCase {

    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    @Autowired
    private lateinit var tokenProvider: JwtTokenProvider
    private val logger = Logger.getLogger(AuthUseCaseImpl::class.java.name)

    override fun signin(data: AccountCredentialsRequest): ResponseEntity<*> {
        logger.info("usecase-auth nome -> ${data.userName}")
        return try {
            val username = data.userName
            val password = data.password

//            authenticationManager
//                .authenticate(UsernamePasswordAuthenticationToken(username, password))

            var user: Users = Users()
            try {
                logger.info("usecase-auth buscando o banco de dados por nome -> ${data.userName}")
                user = userRepository.findByUserName(username)!!
            } catch (exception: Exception) {
                exception.stackTrace
                println("erro $exception")
                println("message ${exception.message}")
                println("cause ${exception.cause}")
            }

            val tokenResponse: TokenResponse =
                tokenProvider.createAccesToken(username!!, user.roles)
            ResponseEntity.ok(tokenResponse)
        } catch (exception: AuthenticationException) {
            throw BadCredentialsException("usuario o senha invalido")
        }
    }

    override fun refreshToken(username: String, refreshToken: String): ResponseEntity<*> {
        logger.info("trying log refresh token do usuario ${username}")

        val user = userRepository.findByUserName(username)
        val tokenResponse: TokenResponse = if (user != null) {
            tokenProvider.refreshToken(refreshToken)
        } else {
            throw UsernameNotFoundException("username: $username not found..")
        }
        return ResponseEntity.ok(tokenResponse)
    }
}