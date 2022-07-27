package br.com.softwalter.domain.usecase.v1

import br.com.softwalter.domain.model.Pessoa
import br.com.softwalter.presentation.pessoa.dto.v1.PessoaResponse

interface PessoaUseCase {

    fun buscarPessoaPorId(idPessoa: Long) : PessoaResponse?

    fun buscarPessoas() : List<PessoaResponse>

    fun salvarPessoa(pessoa: Pessoa) : PessoaResponse?
}