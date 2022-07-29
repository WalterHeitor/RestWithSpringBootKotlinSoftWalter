package br.com.softwalter.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class PessoaNullException : RuntimeException {
    constructor(): super("Não e permitido percistir o objeto no banco")
    constructor(mensage: String?): super(mensage)

}