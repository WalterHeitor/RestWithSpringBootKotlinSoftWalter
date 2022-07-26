package br.com.softwalter.presentation.mapper

import br.com.softwalter.domain.model.Pessoa
import br.com.softwalter.presentation.pessoa.dto.PessoaRequest
import br.com.softwalter.presentation.pessoa.dto.PessoaResponse

interface PessoaMapper {
    fun pessoaToPessoaResponse( pessoa: Pessoa) : PessoaResponse
    fun listPessoaToResponsesPessoas(pessoas: MutableList<Pessoa>): List<PessoaResponse>
    fun pessoaRequestToPessoa(pessoaRequest: PessoaRequest) : Pessoa
}