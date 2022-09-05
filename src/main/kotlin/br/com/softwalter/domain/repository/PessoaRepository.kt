package br.com.softwalter.domain.repository

import br.com.softwalter.domain.model.Pessoa
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository


@Repository
interface PessoaRepository : JpaRepository<Pessoa, Long?> {

    @Modifying
    @Query("UPDATE Pessoa p SET p.ativo = false WHERE p.idPessoa =:idPessoa")
    fun desabilitarPessoa(@Param("idPessoa") idPessoa: Long?)

    @Query("SELECT p FROM Pessoa p WHERE p.nome LIKE LOWER(CONCAT ('%',:nome,'%'))")
    fun findPessoaByNome(@Param("nome") nome: String?, pageable: Pageable) : Page<Pessoa>

}