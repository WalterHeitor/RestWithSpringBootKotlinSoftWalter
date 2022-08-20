package br.com.softwalter.security.jwt

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import kotlin.reflect.jvm.internal.impl.load.java.JavaClassesTracker

class JwtConfigurer
    (@field:Autowired private val tokenProvider: JwtTokenProvider
    ) : SecurityConfigurerAdapter<DefaultSecurityFilterChain?, HttpSecurity>() {

    override fun configure(http: HttpSecurity?) {
        val custonFilter = JwtTokenFiltter(tokenProvider)
        http!!.addFilterBefore(custonFilter, UsernamePasswordAuthenticationFilter::class.java)
    }
}