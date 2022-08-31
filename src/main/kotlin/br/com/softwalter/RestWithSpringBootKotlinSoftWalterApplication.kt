package br.com.softwalter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.crypto.password.DelegatingPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder

@SpringBootApplication
class RestWithSpringBootKotlinSoftWalterApplication

fun main(args: Array<String>) {
	runApplication<RestWithSpringBootKotlinSoftWalterApplication>(*args)

	val encoders: MutableMap<String, PasswordEncoder> = HashMap()
	encoders["pbkdf2"] = Pbkdf2PasswordEncoder()
	val passwordEncoder: DelegatingPasswordEncoder = DelegatingPasswordEncoder("pbkdf2", encoders)
	passwordEncoder.setDefaultPasswordEncoderForMatches(Pbkdf2PasswordEncoder())

	val result = passwordEncoder.encode("admin123")
	println("My hash: $result")
}
