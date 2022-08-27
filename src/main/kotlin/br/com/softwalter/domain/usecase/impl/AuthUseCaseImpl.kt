package br.com.softwalter.domain.usecase.impl

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
        logger.info("trying log ser ${data.userName}")
        return try {
            val username = data.userName
            val password = data.password

//            authenticationManager
//                .authenticate(UsernamePasswordAuthenticationToken(username, password))

            val user = userRepository.findByUserName(username)
            val tokenResponse: TokenResponse = if (user != null) {
                tokenProvider.createAccesToken(username!!, user.roles)
            } else {
                throw UsernameNotFoundException("username: $username not found..")
            }
            ResponseEntity.ok(tokenResponse)
        } catch (exception: AuthenticationException) {
            throw BadCredentialsException("usuario o senha invalido")
        }
    }
}