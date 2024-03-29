package br.com.softwalter.security.jwt

import br.com.softwalter.exceptions.InvalidJwtAutenticationException
import br.com.softwalter.presentation.users.TokenResponse
import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.*
import javax.annotation.PostConstruct
import javax.servlet.http.HttpServletRequest

@Service
class JwtTokenProvider {

    @Value("\${security.jwt.token.secret-key:secret}")
    private var secretKey = "secret"
    @Value("\${security.jwt.token.expire-length:3600000}")
    private var validyMilliseconds: Long = 3_600_000 // 1h

    companion object {
        const val BEARER = "Bearer "
    }

    @Autowired
    private lateinit var userDetailsService: UserDetailsService

    private lateinit var algorithm: Algorithm

    @PostConstruct
    protected  fun init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.toByteArray())
        algorithm = Algorithm.HMAC256(secretKey.toByteArray())
    }

    fun createAccesToken(userName: String, roles: List<String?>) : TokenResponse {

        val now = Date()
        val validity = Date(now.time + validyMilliseconds)
        val accesToken = getAccesstoken(userName, roles, now, validity)
        val refcreshToken = getRefreshToken(userName, roles, now)

        return TokenResponse(
            userName = userName,
            autenticated = true,
            accessToken = accesToken,
            refreshToken = refcreshToken,
            created = now,
            expiration = validity

        )
    }


    fun refreshToken(refreshToken: String) : TokenResponse {

        var token: String = ""
        if (refreshToken.contains(BEARER)) token = refreshToken.substring(BEARER.length)
        val verifier: JWTVerifier = JWT.require(algorithm).build()
        val decodeJWT: DecodedJWT = verifier.verify(token)
        val userName: String = decodeJWT.subject
        val roles: List<String> = decodeJWT.getClaim("roles").asList(String::class.java)
        return createAccesToken(userName, roles)
    }

    private fun getAccesstoken(userName: String, roles: List<String?>, now: Date, validity: Date): String {

        val issuerURL: String = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString()

        return JWT.create()
            .withClaim("roles", roles)
            .withIssuedAt(now)
            .withExpiresAt(validity)
            .withSubject(userName)
            .withIssuer(issuerURL)
            .sign(algorithm)
            .trim()
    }

    private fun getRefreshToken(userName: String, roles: List<String?>, now: Date): String {

        val validityRefreshToken = Date(now.time + validyMilliseconds * 3)

        return JWT.create()
            .withClaim("roles", roles)
            .withExpiresAt(validityRefreshToken)
            .withSubject(userName)
            .sign(algorithm)
            .trim()
    }

    fun getAuthentication(token: String) : Authentication {
        val decoderJWT: DecodedJWT = decodedToken(token)
        val userDetails: UserDetails = userDetailsService.loadUserByUsername(decoderJWT.subject)

        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    private fun decodedToken(token: String): DecodedJWT {
        val algorithm = Algorithm.HMAC256(secretKey.toByteArray())
        val verifier: JWTVerifier = JWT.require(algorithm).build()
        return verifier.verify(token)
    }

    fun resolveToken(req: HttpServletRequest): String? {
        val bearerToken = req.getHeader("Authorization")
        return if (!bearerToken.isNullOrBlank() && bearerToken.startsWith(BEARER)) {
            bearerToken.substring(BEARER.length)
        } else null
    }

    fun validateToken(token: String) : Boolean {

        val decoderJWT = decodedToken(token)
        try {
            if (decoderJWT.expiresAt.before(Date())) false
            return true
        } catch (e: Exception) {
            throw InvalidJwtAutenticationException("Token JWT expirado ou invalido")
        }
    }

}