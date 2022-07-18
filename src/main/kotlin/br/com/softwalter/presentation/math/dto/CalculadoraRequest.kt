package br.com.softwalter.presentation.math.dto

import br.com.softwalter.domain.validatios.Validations

data class CalculadoraRequest(
    val strNumberOne: String,
    val strNumberTwo: String?
){
    fun validaEntrada(strNumberOne: String, strNumberTwo: String,) : CalculadoraRequest {
        Validations.validarSeEntradaENumero(strNumberOne, strNumberTwo)
        return CalculadoraRequest(strNumberOne, strNumberTwo)
    }
}
