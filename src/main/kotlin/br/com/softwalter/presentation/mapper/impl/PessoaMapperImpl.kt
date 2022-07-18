package br.com.softwalter.presentation.mapper.impl

import br.com.softwalter.domain.model.Pessoa
import br.com.softwalter.presentation.mapper.PessoaMapper
import br.com.softwalter.presentation.pessoa.dto.PessoaResponse

class PessoaMapperImpl : PessoaMapper {

    override fun pessoaToPessoaResponse(pessoa: Pessoa): PessoaResponse {
        return PessoaResponse(
            pessoa.idPessoa,
            pessoa.nome,
            pessoa.email
        )
    }
}