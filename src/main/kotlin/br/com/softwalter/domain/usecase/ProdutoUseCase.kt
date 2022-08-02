package br.com.softwalter.domain.usecase

import br.com.softwalter.domain.model.Produto
import br.com.softwalter.presentation.produto.dto.ProdutoRequest
import br.com.softwalter.presentation.produto.dto.ProdutoResponse

interface ProdutoUseCase {
    fun buscarProdutoPorId(idProduto: Long): ProdutoResponse?
    abstract fun buscarProdutos(): List<ProdutoResponse>?
    abstract fun salvarProduto(produto: Produto?): ProdutoResponse?
    fun atualizarProduto(idProduto: Long, produtoRequest: ProdutoRequest): ProdutoResponse?

}
