package br.com.softwalter.domain.validatios

import br.com.softwalter.exceptions.UnsupportedMathOperationException
import br.com.softwalter.presentation.math.dto.CalculadoraRequest

object Validations {

    fun isNumeric(strNumber: String?): Boolean {
        if (strNumber.isNullOrBlank()) return false
        // BR 20,80 US 20.80
        val number = strNumber.replace(",".toRegex(),".")
        return number.matches("""[-+]?[0-9]*\.?[0-9]+""".toRegex())
    }
    fun validarSeEntradaENumero(strNumberOne: String?, strNumberTwo: String?) {
        if (!isNumeric(strNumberOne) || !isNumeric(strNumberTwo)) {
            throw UnsupportedMathOperationException("Erro por favor insira valor numerico")
        }
    }
    fun validaEntrada(strNumberOne: String?, strNumberTwo: String?,) : CalculadoraRequest {
        validarSeEntradaENumero(strNumberOne, strNumberTwo)
        return CalculadoraRequest(strNumberOne!!, strNumberTwo!!)
    }
    fun validaEntradaSqr(strNumber: String?) : CalculadoraRequest {
        if (!isNumeric(strNumber)) throw UnsupportedMathOperationException("Erro por favor insira valor numerico")
        return CalculadoraRequest(strNumber!!, null)
    }
}