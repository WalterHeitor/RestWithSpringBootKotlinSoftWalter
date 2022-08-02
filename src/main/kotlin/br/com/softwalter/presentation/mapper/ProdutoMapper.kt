package br.com.softwalter.presentation.mapper

import br.com.softwalter.domain.model.Produto
import br.com.softwalter.presentation.produto.dto.ProdutoRequest
import br.com.softwalter.presentation.produto.dto.ProdutoResponse

interface ProdutoMapper {
    fun produtoToProdutoResponse(produto: Produto): ProdutoResponse
    abstract fun pessoasToListResponse(pessoas: MutableList<Produto>): List<ProdutoResponse>
    abstract fun produtoRequestToProduto(produtoRequest: ProdutoRequest): Produto

}


