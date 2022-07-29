package br.com.softwalter.templates

import br.com.softwalter.presentation.pessoa.dto.v1.PessoaResponse

object PessoaResponseMockFactory {
    fun criarPessoaResponse(): PessoaResponse? {

        return PessoaResponse(1, "Walter", "walter@email")
    }

}
