package br.com.softwalter.presentation.mapper

import br.com.softwalter.domain.model.Pessoa
import br.com.softwalter.presentation.pessoa.dto.PessoaResponse

interface PessoaMapper {
    fun pessoaToPessoaResponse( pessoa: Pessoa) : PessoaResponse
}