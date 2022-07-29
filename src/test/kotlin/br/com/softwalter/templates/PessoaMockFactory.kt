package br.com.softwalter.templates

import br.com.softwalter.domain.model.Pessoa

object PessoaMockFactory {
    fun criarPessoa(): Pessoa {

        return Pessoa(1, "Walter", "walter@email")
    }

}
