package br.com.softwalter.config

import br.com.softwalter.security.jwt.JwtConfigurer
import br.com.softwalter.security.jwt.JwtTokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.password.DelegatingPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder

@Configuration
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var tokenProvider: JwtTokenProvider

    fun passwordEncoder(): PasswordEncoder {
        val endoders: MutableMap<String, PasswordEncoder> = HashMap<String, PasswordEncoder>()
        endoders["pbkdf2"] = Pbkdf2PasswordEncoder()
        val passwordEncoder: DelegatingPasswordEncoder = DelegatingPasswordEncoder("pbkdf2", endoders)
        passwordEncoder.setDefaultPasswordEncoderForMatches(Pbkdf2PasswordEncoder())
        return passwordEncoder
    }

    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    override fun configure(http: HttpSecurity?) {
        http!!
            .httpBasic().disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers("/auth/signin", "/auth/refresh", "/api-docs/**", "swagger-ui/index.html**").permitAll()
            .antMatchers("").authenticated()
            .antMatchers("").denyAll()
            .and()
            .cors()
            .and()
            .apply(JwtConfigurer(tokenProvider))
    }

}