package br.com.softwalter.domain.repository

import br.com.softwalter.domain.model.Produto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProdutoRepository : JpaRepository<Produto, Long?> {
}