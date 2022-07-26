package br.com.softwalter.data.repository

import br.com.softwalter.domain.model.Pessoa

interface PessoaRepositoryInterface {

    fun <S : Pessoa?> save(entity: S): S

    fun findAll(): MutableList<Pessoa>

    fun deleteById(id: Long)

    fun findById(id: Long): Pessoa?
}