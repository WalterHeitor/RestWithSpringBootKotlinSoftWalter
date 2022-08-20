package br.com.softwalter.exceptions

import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.Exception

@ResponseStatus(HttpStatus.FORBIDDEN)
class InvalidJwtAutenticationException(exception: String?)
    : AuthenticationException(exception)