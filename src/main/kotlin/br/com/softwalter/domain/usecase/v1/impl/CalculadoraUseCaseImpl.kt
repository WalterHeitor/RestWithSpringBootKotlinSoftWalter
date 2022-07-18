package br.com.softwalter.domain.usecase.v1.impl

import br.com.softwalter.domain.usecase.v1.CalculadoraUseCase
import br.com.softwalter.domain.validatios.Validations
import br.com.softwalter.exceptions.UnsupportedMathOperationException
import br.com.softwalter.utils.Convert
import org.springframework.stereotype.Component

@Component
class CalculadoraUseCaseImpl : CalculadoraUseCase {
    override fun adicao(strNumberOne: String, strNumberTwo: String): Double {
        validarSeEntradaENumero(strNumberOne, strNumberTwo)
        return soma(strNumberOne, strNumberTwo)
    }

    override fun subtracao(strNumberOne: String, strNumberTwo: String): Double {
        TODO("Not yet implemented")
    }

    override fun divisao(strNumberOne: String, strNumberTwo: String): Double {
        TODO("Not yet implemented")
    }

    override fun multiplicacao(strNumberOne: String, strNumberTwo: String): Double {
        TODO("Not yet implemented")
    }

    override fun raizQuadrada(strNumber: String): Double {
        TODO("Not yet implemented")
    }

    private fun soma(strNumberOne: String, strNumberTwo: String) =
        Convert.convertDouble(strNumberOne) + Convert.convertDouble(strNumberTwo)

    private fun validarSeEntradaENumero(strNumberOne: String, strNumberTwo: String) {
        if (!Validations.isNumeric(strNumberOne) || !Validations.isNumeric(strNumberTwo)) {
            throw UnsupportedMathOperationException("Erro por favor insira valor numerico")
        }
    }
}