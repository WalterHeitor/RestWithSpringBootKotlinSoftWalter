package br.com.softwalter.presentation.mapper

import br.com.softwalter.domain.model.Pessoa
import br.com.softwalter.presentation.pessoa.dto.v1.PessoaRequest
import br.com.softwalter.presentation.pessoa.dto.v2.PessoaRequest as PessoaRequestv2
import br.com.softwalter.presentation.pessoa.dto.v1.PessoaResponse

interface PessoaMapper {
    fun pessoaToPessoaResponse( pessoa: Pessoa) : PessoaResponse
    fun listPessoaToResponsesPessoas(pessoas: MutableList<Pessoa>): List<PessoaResponse>
    fun pessoaRequestToPessoa(pessoaRequest: PessoaRequest) : Pessoa
    fun pessoaRequestToPessoav2(pessoaRequest: PessoaRequestv2): Pessoa
}