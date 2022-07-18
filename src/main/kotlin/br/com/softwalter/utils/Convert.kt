package br.com.softwalter.utils

import br.com.softwalter.domain.validatios.Validations

object Convert {
    fun convertDouble(strNumber: String?): Double {
        if (strNumber.isNullOrBlank()) return 0.0
        // BR 20,80 US 20.80
        val number = strNumber.replace(",".toRegex(), ".")
        return if (Validations.isNumeric(number)) number.toDouble() else 0.0
    }
}