package br.com.softwalter.templates

import br.com.softwalter.domain.model.Pessoa
import br.com.softwalter.presentation.pessoa.dto.v1.PessoaResponse

object PessoaResponseMockFactory {
    fun criarPessoaResponse(): PessoaResponse? {

        return PessoaResponse(1, "Walter", "walter@email")
    }

    fun criarPessoasResponses(): MutableList<PessoaResponse> {

        return mutableListOf<PessoaResponse>(
            PessoaResponse(1, "Walter", "walter@email"),
            PessoaResponse(2, "heitor", "heitor@email"),
            PessoaResponse(3, "maria", "maria@email")
        )
    }

}
