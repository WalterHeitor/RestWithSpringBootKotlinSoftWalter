package br.com.softwalter.presentation.produto.dto

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.hateoas.RepresentationModel

data class ProdutoResponse(
    @field:JsonProperty("codigo_produto")
    val codigoProduto: Long,
    @field:JsonProperty("nome")
    val nome: String,
    @field:JsonProperty("descricao")
    val descricao: String,
    @field:JsonProperty("preco_custo")
    val precoCusto: Float,
    @field:JsonProperty("preco_venda")
    val precoVenda: Float,
    @field:JsonProperty("quantidade")
    val quantidade: Int,
    @field:JsonProperty("quantidade_minima")
    val quantidadeMinima: Int
) : RepresentationModel<ProdutoResponse>() {
}