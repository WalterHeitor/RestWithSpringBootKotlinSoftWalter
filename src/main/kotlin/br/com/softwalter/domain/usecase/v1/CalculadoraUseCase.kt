package br.com.softwalter.domain.usecase.v1

interface CalculadoraUseCase {

    fun adicao (strNumberOne: String, strNumberTwo: String) : Double
    fun subtracao (strNumberOne: String, strNumberTwo: String) : Double
    fun divisao (strNumberOne: String, strNumberTwo: String) : Double
    fun multiplicacao (strNumberOne: String, strNumberTwo: String) : Double
    fun raizQuadrada (strNumber: String) : Double

}