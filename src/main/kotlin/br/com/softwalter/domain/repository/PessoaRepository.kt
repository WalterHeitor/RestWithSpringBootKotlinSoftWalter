package br.com.softwalter.domain.repository

import br.com.softwalter.domain.model.Pessoa
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PessoaRepository : JpaRepository<Pessoa, Long?> {
}