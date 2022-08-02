package br.com.softwalter.presentation.mapper.impl

import br.com.softwalter.domain.model.Produto
import br.com.softwalter.presentation.mapper.ProdutoMapper
import br.com.softwalter.presentation.produto.dto.ProdutoRequest
import br.com.softwalter.presentation.produto.dto.ProdutoResponse
import org.springframework.stereotype.Component

@Component
class ProdutoMapperImpl : ProdutoMapper {
    override fun produtoToProdutoResponse(produto: Produto): ProdutoResponse {
        return ProdutoResponse(
            codigoProduto = produto.codigoProduto!!,
            nome = produto.nome,
            descricao = produto.descricao,
            precoCusto = produto.precoCusto,
            precoVenda = produto.precoVenda,
            quantidade = produto.quantidade,
            quantidadeMinima = produto.quantidadeMinima
        )
    }

    override fun pessoasToListResponse(produtos: MutableList<Produto>): List<ProdutoResponse> {
        return produtos.map { produto -> ProdutoResponse(
            codigoProduto = produto.codigoProduto!!,
            nome = produto.nome,
            descricao = produto.descricao,
            precoCusto = produto.precoCusto,
            precoVenda = produto.precoVenda,
            quantidade = produto.quantidade,
            quantidadeMinima = produto.quantidadeMinima
        ) }.toList()
    }

    override fun produtoRequestToProduto(produtoRequest: ProdutoRequest): Produto {
        return Produto(
            nome = produtoRequest.nome,
            descricao = produtoRequest.descricao,
            precoCusto = produtoRequest.precoCusto,
            precoVenda = produtoRequest.precoVenda,
            quantidade = produtoRequest.quantidade,
            quantidadeMinima = produtoRequest.quantidadeMinima
        )
    }

}