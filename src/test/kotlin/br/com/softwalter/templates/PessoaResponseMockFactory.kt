package br.com.softwalter.templates

import br.com.softwalter.domain.model.Pessoa
import br.com.softwalter.presentation.pessoa.dto.v1.PessoaResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl

object PessoaResponseMockFactory {
    fun criarPessoaResponse(): PessoaResponse? {

        return PessoaResponse(1, "Walter", "walter@email", true)
    }

    fun criarPessoasResponses(): Page<PessoaResponse> {

        return PageImpl(
            mutableListOf<PessoaResponse>(
                PessoaResponse(1, "Walter", "walter@email", true),
                PessoaResponse(2, "heitor", "heitor@email", true),
                PessoaResponse(3, "maria", "maria@email", true)
            )
        )
    }

}
