package br.com.softwalter.domain.usecase

import br.com.softwalter.domain.model.Pessoa
import br.com.softwalter.presentation.pessoa.dto.v1.PessoaResponse

interface AuthUseCase {

    fun buscarPessoaPorId(idPessoa: Long) : PessoaResponse?

    fun salvarPessoa(pessoa: Pessoa?) : PessoaResponse?

    fun buscarPessoas(): List<PessoaResponse>

    fun atualizarPessoa(idPessoa: Long): PessoaResponse?
}