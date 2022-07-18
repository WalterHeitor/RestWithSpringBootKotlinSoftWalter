package br.com.softwalter.presentation.math

import br.com.softwalter.domain.usecase.v1.CalculadoraUseCase
import br.com.softwalter.domain.validatios.Validations
import br.com.softwalter.presentation.math.dto.CalculadoraRequest
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

@RestController
class MathController(val calculadoraUseCase: CalculadoraUseCase) {
    val counter: AtomicLong = AtomicLong()

    @RequestMapping(value = ["/sum/{numberOne}/{numberTwo}"])
    fun sum(
        @PathVariable(value = "numberOne") numberOne: String?,
        @PathVariable(value = "numberTwo") numberTwo: String?
    ): Double {
        val calculadoraRequest: CalculadoraRequest = Validations.validaEntrada(numberOne, numberTwo)
        return calculadoraUseCase.adicao(calculadoraRequest.strNumberOne, calculadoraRequest.strNumberTwo!!)
    }

    @RequestMapping(value = ["/subtraction/{numberOne}/{numberTwo}"])
    fun subtraction(
        @PathVariable(value = "numberOne") numberOne: String?,
        @PathVariable(value = "numberTwo") numberTwo: String?
    ): Double {
        val calculadoraRequest: CalculadoraRequest = Validations.validaEntrada(numberOne, numberTwo)
        return calculadoraUseCase.subtracao(calculadoraRequest.strNumberOne, calculadoraRequest.strNumberTwo!!)

    }

    @RequestMapping(value = ["/mutiplic/{numberOne}/{numberTwo}"])
    fun mutiplic(
        @PathVariable(value = "numberOne") numberOne: String?,
        @PathVariable(value = "numberTwo") numberTwo: String?
    ): Double {
        val calculadoraRequest: CalculadoraRequest = Validations.validaEntrada(numberOne, numberTwo)
        return calculadoraUseCase.multiplicacao(calculadoraRequest.strNumberOne, calculadoraRequest.strNumberTwo!!)

    }

    @RequestMapping(value = ["/raiz/{number}"])
    fun raiz(
        @PathVariable(value = "number") number: String?
    ): Double {
        val calculadoraRequest: CalculadoraRequest = Validations.validaEntradaSqr(number)
        return calculadoraUseCase.adicao(calculadoraRequest.strNumberOne, calculadoraRequest.strNumberTwo!!)
    }
}