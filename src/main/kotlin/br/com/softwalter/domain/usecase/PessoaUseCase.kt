package br.com.softwalter.domain.usecase

import br.com.softwalter.domain.model.Pessoa
import br.com.softwalter.presentation.pessoa.dto.v1.PessoaResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.PagedModel

interface PessoaUseCase {

    fun buscarPessoaPorId(idPessoa: Long) : PessoaResponse?

    fun salvarPessoa(pessoa: Pessoa?) : PessoaResponse?

    fun buscarPessoas(pageable: Pageable): PagedModel<EntityModel<PessoaResponse>>

    fun findPessoaByNome(nome: String, pageable: Pageable): PagedModel<EntityModel<PessoaResponse>>

    fun atualizarPessoa(idPessoa: Long): PessoaResponse?

    fun desbilitarPessoaPorId(idPessoa: Long): PessoaResponse?
}