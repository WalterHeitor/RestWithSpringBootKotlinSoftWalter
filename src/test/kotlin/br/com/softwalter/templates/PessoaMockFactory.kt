package br.com.softwalter.templates

import br.com.softwalter.domain.model.Pessoa

object PessoaMockFactory {
    fun criarPessoa(): Pessoa {

        return Pessoa(1, "Walter", "walter@email")
    }

    fun criarPessoas(): MutableList<Pessoa> {
        return mutableListOf<Pessoa>(
            Pessoa(1, "Walter", "walter@email"),
            Pessoa(2, "heitor", "heitor@email"),
            Pessoa(3, "maria", "maria@email"))
    }
}
