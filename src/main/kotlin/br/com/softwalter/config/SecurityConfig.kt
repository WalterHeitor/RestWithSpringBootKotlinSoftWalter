package br.com.softwalter.config

import br.com.softwalter.security.jwt.JwtConfigurer
import br.com.softwalter.security.jwt.JwtTokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
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

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        val endoders: MutableMap<String, PasswordEncoder> = HashMap<String, PasswordEncoder>()
        endoders["pbkdf2"] = Pbkdf2PasswordEncoder()
        val passwordEncoder: DelegatingPasswordEncoder = DelegatingPasswordEncoder("pbkdf2", endoders)
        passwordEncoder.setDefaultPasswordEncoderForMatches(Pbkdf2PasswordEncoder())
        return passwordEncoder
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }


    override fun configure(http: HttpSecurity?) {
        http!!
            .httpBasic().disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .headers().frameOptions().disable()//habilitar h2
            .and()
                .authorizeRequests()
                .antMatchers(
                    "/auth/signin", "/auth/refresh",
                    "/api-docs/**", "/swagger-ui/**",
                    "/cadastro/v1/pessoas/**", "*/h2-console/**",
                "/auth/**")
                .permitAll()
            .antMatchers("/cadastro/v1/produtos/**").authenticated()
            .antMatchers("/cadastro/v1/pessoas/**").authenticated()
            .antMatchers("/users").denyAll()
            .and()
            .cors()
            .and()
            .apply(JwtConfigurer(tokenProvider))
    }
}