package br.com.softwalter.templates

import br.com.softwalter.domain.model.Pessoa
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl

object PessoaMockFactory {
    fun criarPessoa(): Pessoa {

        return Pessoa(1, "Walter", "walter@email")
    }

    fun criarPessoas(): Page<Pessoa> {

        return PageImpl(
            mutableListOf<Pessoa>(
            Pessoa(1, "Walter", "walter@email"),
            Pessoa(2, "heitor", "heitor@email"),
            Pessoa(3, "maria", "maria@email"))
        )
    }
}
